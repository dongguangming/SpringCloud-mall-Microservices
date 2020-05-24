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

package com.example.rbac.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * spring-boot-plus属性配置信息
 *
 * @author geekidea
 * @date 2019-08-04
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-plus")
public class SpringBootPlusProperties {

    /**
     * 是否启用ansi控制台输出有颜色的字体，local环境建议开启，服务器环境设置为false
     */
    private boolean enableAnsi;

    /**
     * 项目IP或域名地址
     */
    private String serverIp;

    /**
     * 是否启用验证码
     */
    private boolean enableVerifyCode;

    /**
     * 新建登录用户初始化盐值
     */
    private String loginInitSalt;

    /**
     * 新建登录用户初始化密码
     */
    private String loginInitPassword;

    /**
     * 新建用户初始化头像
     */
    private String loginInitHead;

    /**
     * 实现BaseEnum接口的枚举包
     */
    private String[] enumPackages;

    /**
     * 拦截器配置
     */
    @NestedConfigurationProperty
    private SpringBootPlusInterceptorProperties interceptor;

    /**
     * 过滤器配置
     */
    @NestedConfigurationProperty
    private SpringBootPlusFilterProperties filter;

    /**
     * 上传目录
     */
    private String uploadPath;
    /**
     * 资源访问路径，前端访问
     */
    private String resourceAccessPath;
    /**
     * 资源访问路径，后段配置，资源映射/拦截器使用
     */
    private String resourceAccessPatterns;
    /**
     * 资源访问全路径
     */
    private String resourceAccessUrl;

    /**
     * 允许上传的文件后缀集合
     */
    private List<String> allowUploadFileExtensions;
    /**
     * 允许下载的文件后缀集合
     */
    private List<String> allowDownloadFileExtensions;

    /**
     * JWT配置
     */
    @NestedConfigurationProperty
    private JwtProperties jwt;

    /**
     * Shiro配置
     */
    @NestedConfigurationProperty
    private ShiroProperties shiro = new ShiroProperties();

    /**
     * 项目静态资源访问配置
     *
     * @see SpringBootPlusWebMvcConfig addResourceHandlers
     */
    private String resourceHandlers;

    /**
     * 跨域配置
     */
    @NestedConfigurationProperty
    private SpringBootPlusCorsProperties cors = new SpringBootPlusCorsProperties();

    /**
     * Swagger路径
     */
    private List<String> swaggerPaths;

	public boolean isEnableAnsi() {
		return enableAnsi;
	}

	public void setEnableAnsi(boolean enableAnsi) {
		this.enableAnsi = enableAnsi;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public boolean isEnableVerifyCode() {
		return enableVerifyCode;
	}

	public void setEnableVerifyCode(boolean enableVerifyCode) {
		this.enableVerifyCode = enableVerifyCode;
	}

	public String getLoginInitSalt() {
		return loginInitSalt;
	}

	public void setLoginInitSalt(String loginInitSalt) {
		this.loginInitSalt = loginInitSalt;
	}

	public String getLoginInitPassword() {
		return loginInitPassword;
	}

	public void setLoginInitPassword(String loginInitPassword) {
		this.loginInitPassword = loginInitPassword;
	}

	public String getLoginInitHead() {
		return loginInitHead;
	}

	public void setLoginInitHead(String loginInitHead) {
		this.loginInitHead = loginInitHead;
	}

	public String[] getEnumPackages() {
		return enumPackages;
	}

	public void setEnumPackages(String[] enumPackages) {
		this.enumPackages = enumPackages;
	}

	public SpringBootPlusInterceptorProperties getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(SpringBootPlusInterceptorProperties interceptor) {
		this.interceptor = interceptor;
	}

	public SpringBootPlusFilterProperties getFilter() {
		return filter;
	}

	public void setFilter(SpringBootPlusFilterProperties filter) {
		this.filter = filter;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getResourceAccessPath() {
		return resourceAccessPath;
	}

	public void setResourceAccessPath(String resourceAccessPath) {
		this.resourceAccessPath = resourceAccessPath;
	}

	public String getResourceAccessPatterns() {
		return resourceAccessPatterns;
	}

	public void setResourceAccessPatterns(String resourceAccessPatterns) {
		this.resourceAccessPatterns = resourceAccessPatterns;
	}

	public String getResourceAccessUrl() {
		return resourceAccessUrl;
	}

	public void setResourceAccessUrl(String resourceAccessUrl) {
		this.resourceAccessUrl = resourceAccessUrl;
	}

	public List<String> getAllowUploadFileExtensions() {
		return allowUploadFileExtensions;
	}

	public void setAllowUploadFileExtensions(List<String> allowUploadFileExtensions) {
		this.allowUploadFileExtensions = allowUploadFileExtensions;
	}

	public List<String> getAllowDownloadFileExtensions() {
		return allowDownloadFileExtensions;
	}

	public void setAllowDownloadFileExtensions(
			List<String> allowDownloadFileExtensions) {
		this.allowDownloadFileExtensions = allowDownloadFileExtensions;
	}

	public JwtProperties getJwt() {
		return jwt;
	}

	public void setJwt(JwtProperties jwt) {
		this.jwt = jwt;
	}

	public ShiroProperties getShiro() {
		return shiro;
	}

	public void setShiro(ShiroProperties shiro) {
		this.shiro = shiro;
	}

	public String getResourceHandlers() {
		return resourceHandlers;
	}

	public void setResourceHandlers(String resourceHandlers) {
		this.resourceHandlers = resourceHandlers;
	}

	public SpringBootPlusCorsProperties getCors() {
		return cors;
	}

	public void setCors(SpringBootPlusCorsProperties cors) {
		this.cors = cors;
	}

	public List<String> getSwaggerPaths() {
		return swaggerPaths;
	}

	public void setSwaggerPaths(List<String> swaggerPaths) {
		this.swaggerPaths = swaggerPaths;
	}
    
    
}
