package com.example.demo.service.impl;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.example.demo.common.ConstantUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

/**
 * @author dgm
 * @describe ""
 * @date 2020年5月21日
 */
@Service("userService")
public class UserServiceImpl  extends BaseServiceImpl<User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("username", username);
		List<User> userList = userMapper.selectByExample(example);

		if (userList.size() == 1) {
			return userList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Boolean comparePassword(String pwd, String inputpwd) {
		return pwd.equals(inputpwd);
	}

	@Override
	public String getToken(User user) {
		String token = "";
		try {
			token = JWT.create().withAudience(user.getId().toString())//.withExpiresAt(new Date(System.currentTimeMillis() + (2 * 60 * 60 * 1000))) // 将 user
																		// id
																		// 保存到
																		// token
																		// 里面
					.sign(Algorithm.HMAC256(user.getPassword())); // 以 password
																	// 作为 token
																	// 的密钥
		} catch (UnsupportedEncodingException ignore) {
		}
		return token;
	}

	@Override
	public int createUser(User user) {
		Date now = new Date();
		user.setState(ConstantUtil.USERSTATE_ENABLED);
		user.setCreatetime(null == user.getCreatetime() ? now : user.getCreatetime());
		user.setUptime(null == user.getUptime() ? now : user.getUptime());
		return userMapper.insertSelective(user);
	}

	@Override
	public Boolean isUsernameValid(String username) {
		if (username.matches("^[a-z0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getClusterUsageAll() {
		// 创建中或创建成功算作正在使用
		List<Integer> status = new ArrayList<Integer>();
		status.add(ConstantUtil.APPSTATUS_CREATING);
		status.add(ConstantUtil.APPSTATUS_SUCCESS);
		Example example = new Example(User.class);
		example.createCriteria().andIn("appstatus", status);
		return userMapper.selectCountByExample(example);
	}

	@Override
	public int getClusterUsageByUserLevel(Integer level,Integer system) {
		// 创建中或创建成功算作正在使用
		List<Integer> status = new ArrayList<Integer>();
		status.add(ConstantUtil.APPSTATUS_CREATING);
		status.add(ConstantUtil.APPSTATUS_SUCCESS);
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("appstatus", status);
		criteria.andEqualTo("level", level);
		if(system>0) {
			criteria.andEqualTo("system", system);
		}
		return userMapper.selectCountByExample(example);
	}

	@Override
	public int resetUserExpStatus(User user) {
		user.setEid(0);
		user.setAppstatus(ConstantUtil.APPSTATUS_UNUSED);
		user.setRetrytimes(0);
		user.setAppid(0);
		user.setStepid(0);
		user.setCid(0);
		user.setTcid(0);
		user.setCtid(0);
		user.setComefrom(0);
		user.setSystem(1);
		user.setNotebook("");
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int resetUserFreeStatus(User user) {
		user.setAppstatus(ConstantUtil.APPSTATUS_UNUSED);
		user.setRetrytimes(0);
		user.setAppid(0);
		user.setMode(0);
		user.setSystem(1);
		user.setNotebook("");
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int resetUserInitStatus(Long uid) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("id", uid);
		
		User user = new User();
		user.setEid(0);
		user.setStepid(0);
		user.setAppstatus(ConstantUtil.APPSTATUS_UNUSED);
		user.setRetrytimes(0);
		user.setAppid(0);
		user.setMode(0);
		user.setSystem(1);
		user.setNotebook("");
		return userMapper.updateByExampleSelective(user, example);
	}

	@Override
	public int setUserToken(Long uid, String token) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("id", uid);
		
		User user = new User();
		user.setToken(token);
		return userMapper.updateByExampleSelective(user, example);
	}

	/**
	 * 查询可用用户列表(不查自己)
	 */
	@Override
	public List<User> listUser(Long uid) {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo("id", uid);
		criteria.andEqualTo("state", ConstantUtil.DISPLAY_ENABLE);
		return userMapper.selectByExample(example);
	}
     
}
