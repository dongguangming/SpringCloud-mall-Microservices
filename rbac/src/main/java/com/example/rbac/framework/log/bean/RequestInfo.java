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

package com.example.rbac.framework.log.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.rbac.framework.ip.entity.IpAddress;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * HTTP请求信息对象
 *
 * @author geekidea
 * @date 2020/3/18
 **/
@Data
@Accessors(chain = true)
public class RequestInfo implements Serializable {
    private static final long serialVersionUID = 1421424612944015973L;

    /**
     * 请求路径
     * /api/foobar/add
     */
    private String path;

    /**
     * 请求ID
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String requestId;

    /**
     * 请求实际路径
     * /foobar/add
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String realPath;

    /**
     * 请求IP地址
     */
    private String ip;

    /**
     * 请求IP对象
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private IpAddress ipAddress;

    /**
     * 请求方式，GET/POST
     */
    private String requestMethod;

    /**
     * 请求内容类型
     */
    private String contentType;

    /**
     * 判断控制器方法参数中是否有RequestBody注解
     */
    private Boolean requestBody;

    /**
     * 请求参数对象
     */
    private Object param;

    /**
     * 请求时间字符串
     */
    private String time;

    /**
     * 请求token
     */
    private String token;

    /**
     * 请求token MD5值
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String tokenMd5;

    /**
     * 用户代理字符串
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private String userAgent;

    /**
     * requiresRoles值
     */
    private String requiresRoles;

    /**
     * requiresPermissions值
     */
    private String requiresPermissions;

    /**
     * requiresAuthentication
     */
    private Boolean requiresAuthentication;

    /**
     * requiresUser
     */
    private Boolean requiresUser;

    /**
     * requiresGuest
     */
    private Boolean requiresGuest;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public IpAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(IpAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Boolean getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Boolean requestBody) {
		this.requestBody = requestBody;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenMd5() {
		return tokenMd5;
	}

	public void setTokenMd5(String tokenMd5) {
		this.tokenMd5 = tokenMd5;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequiresRoles() {
		return requiresRoles;
	}

	public void setRequiresRoles(String requiresRoles) {
		this.requiresRoles = requiresRoles;
	}

	public String getRequiresPermissions() {
		return requiresPermissions;
	}

	public void setRequiresPermissions(String requiresPermissions) {
		this.requiresPermissions = requiresPermissions;
	}

	public Boolean getRequiresAuthentication() {
		return requiresAuthentication;
	}

	public void setRequiresAuthentication(Boolean requiresAuthentication) {
		this.requiresAuthentication = requiresAuthentication;
	}

	public Boolean getRequiresUser() {
		return requiresUser;
	}

	public void setRequiresUser(Boolean requiresUser) {
		this.requiresUser = requiresUser;
	}

	public Boolean getRequiresGuest() {
		return requiresGuest;
	}

	public void setRequiresGuest(Boolean requiresGuest) {
		this.requiresGuest = requiresGuest;
	}

    
}
