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

package com.example.rbac.framework.core.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

import com.example.rbac.framework.common.vo.EnumVo;
import com.example.rbac.framework.common.vo.EnumVo.Builder;

/**
 * Filter请求详情信息
 *
 * @author geekidea
 * @date 2020/3/26
 **/
@Data
@Accessors(chain = true)
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 2543641512850125440L;

	/**
     * 请求ip地址
     */
    private String ip;

    /**
     * 请求路径
     */
    private String path;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	/*构建者模式：静态内部类*/  
    public static class Builder{  
        private RequestDetail createPattern;  
        public Builder() {  
            createPattern=new RequestDetail();  
        }  
          
        public Builder addIp(String ip){  
            createPattern.setIp(ip);  
            return this;  
        }  
        public Builder addPath(String path){  
            createPattern.setPath(path);  
            return this;  
        } 
       
        public RequestDetail buildCreatePattern(){  
            return createPattern;  
        }  
    }  
}
