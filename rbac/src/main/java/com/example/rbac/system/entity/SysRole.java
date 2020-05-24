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

package com.example.rbac.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.rbac.framework.common.entity.BaseEntity;
import com.example.rbac.framework.core.validator.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * <pre>
 * 系统角色
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRole对象", description = "系统角色")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = -487738234353456553L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = Update.class, message = "角色ID不能为空")
    private Long id;

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty("角色唯一编码")
    private String code;

    @ApiModelProperty("角色类型")
    private Integer type;

    @ApiModelProperty("角色状态，0：禁用，1：启用")
    private Integer state;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("版本")
    @Null(message = "版本不用传")
    @Version
    private Integer version;

    @ApiModelProperty("创建时间")
    @Null(message = "创建时间不用传")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @Null(message = "修改时间不用传")
    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}
