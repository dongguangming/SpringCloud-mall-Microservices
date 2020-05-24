package com.example.demo.config;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.annotation.LoginRequired;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

/**
 * @author dgm
 * @describe ""
 * @date 2020年5月21日
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String systemType = request.getHeader("systemType");
        String eduType = request.getHeader("eduType");
        String industryType = request.getHeader("industryType");
        
        request.setAttribute("systemType", systemType);//实验性质，0大数据，1人工智能
        request.setAttribute("eduType", eduType);//学校性质，0专科，1本科
        request.setAttribute("industryType", industryType);//行业性质，0金融，1环境，2地震，3物流
        
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 执行认证
            String token = request.getHeader("token");  // 从 http 请求头中取出 token
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            long userId;
            try {
                userId = Long.parseLong(JWT.decode(token).getAudience().get(0));  // 获取 token 中的 user id
            } catch (JWTDecodeException e) {
                throw new RuntimeException("token无效，请重新登录");
            }
            User user = userService.selectByKey(userId);
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            if(!token.equalsIgnoreCase(user.getToken())){
            	//throw new RuntimeException("token无效，请重新登录");
            	response.sendError(response.SC_UNAUTHORIZED, "token无效，请重新登录");
            	response.setStatus(response.SC_UNAUTHORIZED);
                return false;
            }
            // 验证 token
            try {
                JWTVerifier verifier =  JWT.require(Algorithm.HMAC256(user.getPassword())).build();//acceptExpiresAt(2 * 60 * 60).acceptExpiresAt(3 * 60)
                try {
                    verifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("token无效，请重新登录");
                }
            } catch (UnsupportedEncodingException ignore) {}
            request.setAttribute("currentUser", user);
            return true;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
