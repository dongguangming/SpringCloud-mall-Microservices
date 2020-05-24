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
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 跨域属性配置
 *
 * @author geekidea
 * @date 2019-10-09
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring-boot-plus.cors")
public class SpringBootPlusCorsProperties {

    /**
     * 是否启用跨域，默认启用
     */
    private boolean enable = true;

    /**
     * CORS过滤的路径，默认：/**
     */
    private String path = "/**";

    /**
     * 允许访问的源
     */
    private List<String> allowedOrigins = Collections.singletonList(CorsConfiguration.ALL);

    /**
     * 允许访问的请求头
     */
    private List<String> allowedHeaders = Collections.singletonList(CorsConfiguration.ALL);

    /**
     * 是否允许发送cookie
     */
    private boolean allowCredentials = true;

    /**
     * 允许访问的请求方式
     */
    private List<String> allowedMethods = Collections.singletonList(CorsConfiguration.ALL);

    /**
     * 允许响应的头
     */
    private List<String> exposedHeaders = Arrays.asList("token");

    /**
     * 该响应的有效时间默认为30分钟，在有效时间内，浏览器无须为同一请求再次发起预检请求
     */
    private Long maxAge = 1800L;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getAllowedOrigins() {
		return allowedOrigins;
	}

	public void setAllowedOrigins(List<String> allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	public List<String> getAllowedHeaders() {
		return allowedHeaders;
	}

	public void setAllowedHeaders(List<String> allowedHeaders) {
		this.allowedHeaders = allowedHeaders;
	}

	public boolean isAllowCredentials() {
		return allowCredentials;
	}

	public void setAllowCredentials(boolean allowCredentials) {
		this.allowCredentials = allowCredentials;
	}

	public List<String> getAllowedMethods() {
		return allowedMethods;
	}

	public void setAllowedMethods(List<String> allowedMethods) {
		this.allowedMethods = allowedMethods;
	}

	public List<String> getExposedHeaders() {
		return exposedHeaders;
	}

	public void setExposedHeaders(List<String> exposedHeaders) {
		this.exposedHeaders = exposedHeaders;
	}

	public Long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Long maxAge) {
		this.maxAge = maxAge;
	}
    
}
