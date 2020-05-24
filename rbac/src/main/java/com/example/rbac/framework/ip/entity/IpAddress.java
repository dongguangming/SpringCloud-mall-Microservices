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

package com.example.rbac.framework.ip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.rbac.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * IP地址
 *
 * @author geekidea
 * @since 2020-03-25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IpAddress对象")
public class IpAddress extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String ipStart;

    private String ipEnd;

    @ApiModelProperty("区域")
    private String area;

    @ApiModelProperty("运营商")
    private String operator;

    private Long ipStartNum;

    private Long ipEndNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getIpEnd() {
		return ipEnd;
	}

	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Long getIpStartNum() {
		return ipStartNum;
	}

	public void setIpStartNum(Long ipStartNum) {
		this.ipStartNum = ipStartNum;
	}

	public Long getIpEndNum() {
		return ipEndNum;
	}

	public void setIpEndNum(Long ipEndNum) {
		this.ipEndNum = ipEndNum;
	}
	
	/*构建者模式：静态内部类*/  
    public static class Builder{  
        private IpAddress createPattern;  
        public Builder() {  
            createPattern=new IpAddress();  
        }  
        public Builder addId(Long id){  
            createPattern.setId(id);  
            return this;  
        }  
        public Builder addArea(String area){  
            createPattern.setArea(area);  
            return this;  
        }  
        //...
        public IpAddress buildCreatePattern(){  
            return createPattern;  
        }  
    }  
    
}
