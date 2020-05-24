/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.rbac.framework.shiro.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.rbac.framework.shiro.util.JwtUtil;
import com.example.rbac.framework.util.IpUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.HostAuthenticationToken;

import java.util.Date;

/**
 * Shiro JwtToken对象
 *
 * @author geekidea
 * @date 2019-09-27
 * @since 1.3.0.RELEASE
 **/
@Data
@Accessors(chain = true)
public class JwtToken implements HostAuthenticationToken {
	private static final long serialVersionUID = 5101247566043093405L;
	
	/**
     * 登录ip
     */
    private String host;
    /**
     * 登录用户名称
     */
    private String username;
    /**
     * 登录盐值
     */
    private String salt;
    /**
     * 登录token
     */
    private String token;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 多长时间过期，默认一小时
     */
    private long expireSecond;
    /**
     * 过期日期
     */
    private Date expireDate;

    private String principal;

    private String credentials;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public static JwtToken build(String token, String username, String salt, long expireSecond) {
        DecodedJWT decodedJwt = JwtUtil.getJwtInfo(token);
        Date createDate = decodedJwt.getIssuedAt();
        Date expireDate = decodedJwt.getExpiresAt();
       /* return new JwtToken()
                .setUsername(username)
                .setToken(token)
                .setHost(IpUtil.getRequestIp())
                .setSalt(salt)
                .setCreateDate(createDate)
                .setExpireSecond(expireSecond)
                .setExpireDate(expireDate);*/
    return  new Builder().addUsername(username).addToken(token)
     .addHost(IpUtil.getRequestIp()).addSalt(salt)
     .addCreateDate(createDate).addExpireSecond(expireSecond)
     .addExpireDate(expireDate).buildCreatePattern();
    }
    
    /*构建者模式：静态内部类*/  
    public static class Builder{  
        private JwtToken createPattern;  
        public Builder() {  
            createPattern=new JwtToken();  
        }  
        public Builder addUsername(String username){  
            createPattern.setUsername(username);  
            return this;  
        }  
        public Builder addToken(String token){  
            createPattern.setToken(token);  
            return this;  
        }  
        public Builder addHost(String host){  
            createPattern.setHost(host);  
            return this;  
        } 
        public Builder addSalt(String salt){  
            createPattern.setSalt(salt);  
            return this;  
        } 
        public Builder addCreateDate(Date createDate){  
            createPattern.setCreateDate(createDate);  
            return this;  
        } 
        public Builder addExpireSecond(Long expireSecond){  
            createPattern.setExpireSecond(expireSecond);  
            return this;  
        } 
        public Builder addExpireDate(Date expireDate){  
            createPattern.setExpireDate(expireDate);  
            return this;  
        } 
        public JwtToken buildCreatePattern(){  
            return createPattern;  
        }  
    }  
    

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getExpireSecond() {
		return expireSecond;
	}

	public void setExpireSecond(long expireSecond) {
		this.expireSecond = expireSecond;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

    
}
