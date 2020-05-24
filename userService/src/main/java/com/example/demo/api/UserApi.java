package com.example.demo.api;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.CurrentUser;
import com.example.demo.annotation.LoginRequired;
import com.example.demo.common.ConstantUtil;
import com.example.demo.common.UserLevelUtil;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.github.pagehelper.util.StringUtil;

import net.sf.json.util.WebUtils;

@RestController
@RequestMapping("/user")
/**
 * @author dgm
 * @describe ""
 * @date 2020年5月21日
 */
public class UserApi extends BaseAPI {

	//private static final Logger logger = LoggerFactory.getLogger(UserApi.class);  

	private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    //private static final Map<String, String> credentials = new HashMap<>();
    
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 * @param user
	 * @param force(强制登录标识：0为普通登录，1为强制登录)
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@CrossOrigin
	public JSONObject login(User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession session, 
			@RequestParam(value = "redirect", required = false, defaultValue = "") String redirect,
			@RequestParam(value = "force", required = false , defaultValue = "1") String force) {
		logger.info("用户登录(" + user.getUsername() + "," + user.getPassword() + ")");
		JSONObject jobj = new JSONObject();
		
		// 判断用户名是否存在
		User userInDataBase = userService.findByUsername(user.getUsername());
		if (userInDataBase == null) {
			logger.error("用户不存在");
			jobj.put("result", "fail");
			jobj.put("msg", "用户不存在");
			return jobj;
		}
		// 判断用户是否被禁用
		if (ConstantUtil.USERSTATE_DISABLED == userInDataBase.getState()) {
			logger.error("用户已被禁用");
			jobj.put("result", "fail");
			jobj.put("msg", "用户已被禁用");
			return jobj;
		}
		// 判断密码是否正确
		if (!userService.comparePassword(userInDataBase.getPassword(), user.getPassword()/*MD5.cell32(user.getPassword())*/)) {
			logger.error("密码不正确");
			jobj.put("result", "fail");
			jobj.put("msg", "密码不正确");
			return jobj;
		} else {		
			boolean login = false; //默认没有登录
			String token = userInDataBase.getToken();
			
			//if ( !JwtUtil.isExists(userInDataBase.getId()+"---"+userInDataBase.getUsername()) ) {
			//String sso = JwtUtil.generateToken(userInDataBase.getId()+"---"+userInDataBase.getUsername(), token);
	       // CookieUtil.create(httpServletResponse, jwtTokenCookieName, sso, false, -1, "localhost");
			//Object start = session.getAttribute(user.getUsername());
		    //logger.info("loginin: "+(start==null?start:start.toString())); 
            logger.info("loginin******,token为= "+token); 
	        if(StringUtil.isEmpty(token) || force.equalsIgnoreCase("1")) {
	        		token = userService.getToken(userInDataBase);
	        		userService.setUserToken(userInDataBase.getId(), token);
	        } else {
			    //token = /*StringUtil.isNotEmpty(token) ? token : */userService.getToken(userInDataBase);
			    //userService.setUserToken(userInDataBase.getId(), token);
			    
	        	login = true;
	        }

			logger.info("登录成功，token=" + token);
			jobj.put("result", "success");
			jobj.put("login", login);
			jobj.put("token", token);
			jobj.put("user", userInDataBase);
			jobj.put("redirect", redirect);

			return jobj;
			/*} else {
				logger.info("************已经登录*************");
				jobj.put("msg", "同一账号不能同时登录，已经在其他地方登录");
				jobj.put("result", "fail");
				return jobj;
			}*/
		}
	}

	/**
	 * 用户登出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@LoginRequired
	@CrossOrigin
    public JSONObject logout(@CurrentUser User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession session) {
        //JwtUtil.invalidateRelatedTokens(user.getId()+"---"+user.getUsername(), httpServletRequest);
        //CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
		/*Object start = session.getAttribute(user.getUsername());
	    logger.info("logout: "+start==null?start:start.toString()); 
        if(start!=null) {
        	session.removeAttribute(user.getUsername());
        }*/
		logger.info("用户登出： " + user.getUsername());
		JSONObject jobj = new JSONObject();
		
		// 重置用户token为空
		if (1!=userService.setUserToken(user.getId(), "")) {
			logger.error("用户登出失败");
			jobj.put("result", "fail");
			jobj.put("msg", "用户登出失败");
			return jobj;
		}
		
		logger.info(user.getUsername()+" logout成功******,重置token为空");
		jobj.put("result", "success");
		jobj.put("msg", "用户登出成功");
		jobj.put("redirect", "/");
		return jobj;
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @param orgpwd
	 * @param newpwd
	 * @return
	 */
	@RequestMapping(value = "/modpwd", method = RequestMethod.POST)
	@LoginRequired
	@CrossOrigin
	public JSONObject modpwd(@CurrentUser User user, String newpwd) {
		logger.info("修改用户" + user.getUsername() + "的密码为" + newpwd);
		JSONObject jobj = new JSONObject();

		// 设置用户新密码
		//user.setPassword(MD5.cell32(newpwd));
		user.setPassword(Base64Utils.encodeToString(DigestUtils.md5DigestAsHex(newpwd.getBytes()).getBytes()));
		
		// 更新密码
		if (1 != userService.updateAll(user)) {
			logger.error("用户密码修改失败");
			jobj.put("result", "fail");
			jobj.put("msg", "修改失败");
			return jobj;
		}

		logger.info("用户密码修改成功");
		jobj.put("result", "success");
		jobj.put("msg", "修改成功");
		jobj.put("newtoken", userService.getToken(user));
		return jobj;
	}

	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/checkname/{username}", method = RequestMethod.GET)
	@CrossOrigin
	public JSONObject checkname(@PathVariable String username) {
		logger.info("检查用户名" + username + "是否存在");
		JSONObject jobj = new JSONObject();
		
		boolean exists = false;
		if (null != userService.findByUsername(username)) {
			exists = true;
		}
		
		jobj.put("exists", exists);
		return jobj;
	}
	
	/**
	 * 检查用户名token是否还有效
	 * @param uid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/checktoken", method = RequestMethod.POST)
	@CrossOrigin
	public JSONObject checktoken(Integer uid, String token) {
		logger.info("检查用户uid： " + uid + "的token = " + token +" 是否还有效");
		JSONObject jobj = new JSONObject();
		
		boolean valid = true;
		
		User user = userService.selectByKey(uid);
        if (user == null) {
        	valid = false;
            logger.error("用户不存在，请重新登录");
			jobj.put("result", "fail");
			jobj.put("msg", "用户不存在，请重新登录");
			return jobj;
        }
        if(!token.equalsIgnoreCase(user.getToken())){
        	valid = false;
        	logger.error("token无效，请重新登录");
 			jobj.put("result", "fail");
 			jobj.put("msg", "token无效，请重新登录");
 			return jobj;
        }
        
		jobj.put("valid", valid);
		
		logger.info("验证token用户uid="+uid);
		jobj.put("result", "success");
		jobj.put("msg", "验证用户uid="+uid+"的token"+(valid==true?"合法":"不合法"));
		
		return jobj;
	}
	
	/**
	 * 我的简介，查询用户的级联信息（比如所属班级，和指导老师（针对学生），管理员和老师查基本信息）
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	@LoginRequired
	@CrossOrigin
	public JSONObject getUserInfo(@CurrentUser User user) {
		logger.info("查询用户id=" + user.getId() + "的级联信息");
		JSONObject jobj = new JSONObject();
		
		logger.info("查询成功");
		jobj.put("result", "success");
		jobj.put("msg", "查询用户的级联信息（比如所属班级，和指导老师（针对学生），管理员和老师查基本信息）成功");
		//jobj.put("info", UserLevelUtil.isUserStudent(user)?studentInfo:teacherInfo);
		return jobj;
	}
	
	/**
	 * 我的简介，修改用户的级联信息（比如所属班级，和指导老师（针对学生），管理员和老师查基本信息）
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	@LoginRequired
	@CrossOrigin
	public JSONObject updateUserInfo(@CurrentUser User user,
			@RequestParam(value = "name", required = false, defaultValue = "")String name, 
			@RequestParam(value = "number", required = false, defaultValue = "")String number, 
			@RequestParam(value = "gender", required = false, defaultValue = "1")Integer gender,
			@RequestParam(value = "school", required = false, defaultValue = "")String school, 
			@RequestParam(value = "major", required = false, defaultValue = "")String major, 
			@RequestParam(value = "phone", required = false, defaultValue = "")String phone) {
		
		logger.info("准备修改用户id=" + user.getId() + "的级联信息");
		JSONObject jobj = new JSONObject();
		
		
		return jobj;
	}
	
}
