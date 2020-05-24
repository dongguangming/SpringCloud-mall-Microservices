package com.example.demo.config;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Enumeration;
 
/**
 * Created by jack on 2017/10/25.
 */
 
/**
 * 解决websocket获取不到session的问题
 * 参考：http://www.cnblogs.com/jarviswhj/p/4227559.html
 *       http://www.cnblogs.com/zhaoww/p/5119706.html
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
    private static volatile BeanFactory context;

	@Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //super.modifyHandshake(sec, request, response);
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        //解决httpSession为null的情况
        if (httpSession == null){
            httpSession = new HttpSession() {
                @Override
                public long getCreationTime() {
                    return 0;
                }
 
                @Override
                public String getId() {
                    return null;
                }
 
                @Override
                public long getLastAccessedTime() {
                    return 0;
                }
 
                @Override
                public ServletContext getServletContext() {
                    return null;
                }
 
                @Override
                public void setMaxInactiveInterval(int i) {
 
                }
 
                @Override
                public int getMaxInactiveInterval() {
                    return 0;
                }
 
                @Override
                public HttpSessionContext getSessionContext() {
                    return null;
                }
 
                @Override
                public Object getAttribute(String s) {
                    return null;
                }
 
                @Override
                public Object getValue(String s) {
                    return null;
                }
 
                @Override
                public Enumeration<String> getAttributeNames() {
                    return null;
                }
 
                @Override
                public String[] getValueNames() {
                    return new String[0];
                }
 
                @Override
                public void setAttribute(String s, Object o) {
 
                }
 
                @Override
                public void putValue(String s, Object o) {
 
                }
 
                @Override
                public void removeAttribute(String s) {
 
                }
 
                @Override
                public void removeValue(String s) {
 
                }
 
                @Override
                public void invalidate() {
 
                }
 
                @Override
                public boolean isNew() {
                    return false;
                }
            };
        }
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }

	    @Override
	    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException
	    {
	         return context.getBean(clazz);
	    }

	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	    {
	        System.out.println("auto load "+this.hashCode());
	        GetHttpSessionConfigurator.context = applicationContext;
	    }


}
