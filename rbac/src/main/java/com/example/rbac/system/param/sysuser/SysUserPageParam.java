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

package com.example.rbac.system.param.sysuser;

import com.example.rbac.framework.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <pre>
 * 系统用户 查询参数对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysUserPageParam对象", description = "系统用户查询参数")
public class SysUserPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 7437881671144580610L;

    @ApiModelProperty("部门id")
    private Long departmentId;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("状态，0：禁用，1：启用，2：锁定")
    private Integer state;

    @ApiModelProperty("创建时间开始")
    private Date createTimeStart;

    @ApiModelProperty("创建时间结束")
    private Date createTimeEnd;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
}
