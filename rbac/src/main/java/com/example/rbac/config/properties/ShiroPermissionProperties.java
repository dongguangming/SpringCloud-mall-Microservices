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

/**
 * Shiro权限配置映射类
 *
 * @author geekidea
 * @date 2019-09-28
 **/
@Data
public class ShiroPermissionProperties {

    /**
     * 路径
     */
    private String url;
    /**
     * 路径数组
     */
    private String[] urls;

    /**
     * 权限
     */
    private String permission;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

    
}
