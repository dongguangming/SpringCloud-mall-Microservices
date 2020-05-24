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

package com.example.rbac.framework.common.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * REST API 返回结果
 * </p>
 *
 * @author geekidea
 * @since 2018-11-08
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiResult<T> implements Serializable {
	private static final long serialVersionUID = 8004487252556526569L;

	/**
     * 响应码
     */
    private int code;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public ApiResult() {
        time  = new Date();
    }
    

    public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public static ApiResult<Boolean> result(boolean flag){
        if (flag){
            return ok();
        }
        return fail();
    }

    public static ApiResult<Boolean> result(ApiCode apiCode){
        return result(apiCode,null);
    }

    public static <T> ApiResult<T> result(ApiCode apiCode,T data){
        return result(apiCode,null,data);
    }

    public static <T> ApiResult<T> result(ApiCode apiCode,String message,T data){
        boolean success = false;
        if (apiCode.getCode() == ApiCode.SUCCESS.getCode()){
            success = true;
        }
        String apiMessage = apiCode.getMessage();
        if (StringUtils.isNotBlank(apiMessage)){
            message = apiMessage;
        }
        /*return (ApiResult<T>) ApiResult.builder()
                .code(apiCode.getCode())
                .message(message)
                .data(data)
                .success(success)
                .time(new Date())
                .build();*/
        return (ApiResult<T>) new ApiResult.Builder().addCode(apiCode.getCode()).addMessage(message)
        .addData(data).addSuccess(success).addTime(new Date()).buildCreatePattern();
    }

  
    /*构建者模式：静态内部类*/  
    public static class Builder{  
        private ApiResult createPattern;  
        public Builder() {  
            createPattern=new ApiResult();  
        }  
        public Builder addCode(Integer id){  
            createPattern.setCode(id);  
            return this;  
        }  
        public Builder addMessage(String message){  
            createPattern.setMessage(message);  
            return this;  
        }  
        @SuppressWarnings("unchecked")
		public Builder addData(Object data){  
            createPattern.setData(data);  
            return this;  
        }  
        public Builder addSuccess(boolean success){  
            createPattern.setSuccess(success);  
            return this;  
        }  
        public Builder addTime(Date time){  
            createPattern.setTime(time);  
            return this;  
        }  
        public ApiResult buildCreatePattern(){  
            return createPattern;  
        }  
    }  
    
	public static ApiResult<Boolean> ok(){
        return ok(null);
    }

    public static <T> ApiResult<T> ok(T data){
        return result(ApiCode.SUCCESS,data);
    }

    public static <T> ApiResult<T> ok(T data,String message){
        return result(ApiCode.SUCCESS,message,data);
    }

    public static ApiResult<Map<String,Object>> okMap(String key,Object value){
        Map<String,Object> map = new HashMap<>(1);
        map.put(key,value);
        return ok(map);
    }

    public static ApiResult<Boolean> fail(ApiCode apiCode){
        return result(apiCode,null);
    }

    public static ApiResult<String> fail(String message){
        return result(ApiCode.FAIL,message,null);

    }

    public static <T> ApiResult<T> fail(ApiCode apiCode,T data){
        if (ApiCode.SUCCESS == apiCode){
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode,data);

    }

    public static  ApiResult<String> fail(Integer errorCode,String message){
        return (ApiResult<String>) new ApiResult.Builder().addSuccess(false).addCode(errorCode).addMessage(message).buildCreatePattern();
                /*.setSuccess(false)
                .setCode(errorCode)
                .setMessage(message);*/
    }

    public static ApiResult<Map<String,Object>> fail(String key,Object value){
        Map<String,Object> map = new HashMap<>(1);
        map.put(key,value);
        return result(ApiCode.FAIL,map);
    }

    public static ApiResult<Boolean> fail() {
        return fail(ApiCode.FAIL);
    }
}