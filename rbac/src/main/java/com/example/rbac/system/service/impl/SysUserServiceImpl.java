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

package com.example.rbac.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rbac.config.properties.SpringBootPlusProperties;
import com.example.rbac.framework.common.exception.BusinessException;
import com.example.rbac.framework.common.service.impl.BaseServiceImpl;
import com.example.rbac.framework.core.pagination.PageInfo;
import com.example.rbac.framework.core.pagination.Paging;
import com.example.rbac.framework.shiro.util.SaltUtil;
import com.example.rbac.framework.util.PasswordUtil;
import com.example.rbac.framework.util.PhoneUtil;
import com.example.rbac.system.entity.SysUser;
import com.example.rbac.system.enums.StateEnum;
import com.example.rbac.system.mapper.SysUserMapper;
import com.example.rbac.system.param.sysuser.ResetPasswordParam;
import com.example.rbac.system.param.sysuser.SysUserPageParam;
import com.example.rbac.system.param.sysuser.UpdatePasswordParam;
import com.example.rbac.system.service.SysDepartmentService;
import com.example.rbac.system.service.SysRoleService;
import com.example.rbac.system.service.SysUserService;
import com.example.rbac.system.vo.SysUserQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;


/**
 * <pre>
 * 系统用户 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @Lazy
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SpringBootPlusProperties springBootPlusProperties;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysUser(SysUser sysUser) throws Exception {
        // 校验用户名是否存在
        boolean isExists = isExistsByUsername(sysUser.getUsername());
        if (isExists) {
            throw new BusinessException("用户名已存在");
        }
        // 校验部门和角色
        checkDepartmentAndRole(sysUser.getDepartmentId(), sysUser.getRoleId());
        sysUser.setId(null);

        // 生成盐值
        String salt = null;
        String password = sysUser.getPassword();
        // 如果密码为空，则设置默认密码
        if (StringUtils.isBlank(password)) {
            salt = springBootPlusProperties.getLoginInitSalt();
            password = springBootPlusProperties.getLoginInitPassword();
        } else {
            salt = SaltUtil.generateSalt();
        }
        // 密码加密
        sysUser.setSalt(salt);
        sysUser.setPassword(PasswordUtil.encrypt(password, salt));

        // 如果头像为空，则设置默认头像
        if (StringUtils.isBlank(sysUser.getHead())) {
            sysUser.setHead(springBootPlusProperties.getLoginInitHead());
        }

        // 保存系统用户
        return super.save(sysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysUser(SysUser sysUser) throws Exception {
        // 校验部门和角色
        checkDepartmentAndRole(sysUser.getDepartmentId(), sysUser.getRoleId());
        // 获取系统用户
        SysUser updateSysUser = getById(sysUser.getId());
        if (updateSysUser == null) {
            throw new BusinessException("修改的用户不存在");
        }

        // 修改系统用户
        updateSysUser.setNickname(sysUser.getNickname());
        updateSysUser.setPhone(sysUser.getPhone());
        updateSysUser.setGender(sysUser.getGender());
        updateSysUser.setRemark(sysUser.getRemark());
        updateSysUser.setState(sysUser.getState());
        updateSysUser.setDepartmentId(sysUser.getDepartmentId());
        updateSysUser.setRoleId(sysUser.getRoleId());
        updateSysUser.setUpdateTime(new Date());
        return super.updateById(updateSysUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public SysUserQueryVo getSysUserById(Serializable id) throws Exception {
        return sysUserMapper.getSysUserById(id);
    }

    @Override
    public Paging<SysUserQueryVo> getSysUserPageList(SysUserPageParam sysUserPageParam) throws Exception {
        Page<SysUserQueryVo> page = new PageInfo<>(sysUserPageParam, OrderItem.desc(getLambdaColumn(SysUser::getCreateTime)));
        IPage<SysUserQueryVo> iPage = sysUserMapper.getSysUserPageList(page, sysUserPageParam);

        // 手机号码脱敏处理
        if (iPage != null && CollectionUtils.isNotEmpty(iPage.getRecords())) {
            iPage.getRecords().forEach(vo -> {
                vo.setPhone(PhoneUtil.desensitize(vo.getPhone()));
            });
        }

        return new Paging(iPage);
    }

    @Override
    public boolean isExistsByUsername(String username) throws Exception {
        SysUser selectSysUser = new SysUser();
        selectSysUser.setUsername(username);
        return sysUserMapper.selectCount(new QueryWrapper<>(selectSysUser)) > 0;
    }

    @Override
    public void checkDepartmentAndRole(Long departmentId, Long roleId) throws Exception {
        // 校验部门是否存在并且可用
        boolean isEnableDepartment = sysDepartmentService.isEnableSysDepartment(departmentId);
        if (!isEnableDepartment) {
            throw new BusinessException("该部门不存在或已禁用");
        }
        // 校验角色是否存在并且可用
        boolean isEnableRole = sysRoleService.isEnableSysRole(roleId);
        if (!isEnableRole) {
            throw new BusinessException("该角色不存在或已禁用");
        }
    }

    @Override
    public boolean isExistsSysUserByRoleId(Long roleId) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setState(StateEnum.ENABLE.getCode());
        sysUser.setRoleId(roleId);
        return sysUserMapper.selectCount(new QueryWrapper(sysUser)) > 0;
    }

    @Override
    public boolean updatePassword(UpdatePasswordParam updatePasswordParam) throws Exception {
        String oldPassword = updatePasswordParam.getOldPassword();
        String newPassword = updatePasswordParam.getNewPassword();
        String confirmPassword = updatePasswordParam.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            throw new BusinessException("两次输入的密码不一致");
        }
        if (newPassword.equals(oldPassword)) {
            throw new BusinessException("新密码和旧密码不能一致");
        }

        // 判断原密码是否正确
        SysUser sysUser = getById(updatePasswordParam.getUserId());
        if (sysUser == null) {
            throw new BusinessException("用户不存在");
        }
        if (StateEnum.DISABLE.getCode().equals(sysUser.getState())) {
            throw new BusinessException("用户已禁用");
        }
        // 密码加密处理
        String salt = sysUser.getSalt();
        String encryptOldPassword = PasswordUtil.encrypt(oldPassword, salt);
        if (!sysUser.getPassword().equals(encryptOldPassword)) {
            throw new BusinessException("原密码错误");
        }
        // 新密码加密
        String encryptNewPassword = PasswordUtil.encrypt(newPassword, salt);

        // 修改密码
        SysUser updateSysUser = new SysUser();
        updateSysUser.setId(sysUser.getId());
        updateSysUser.setPassword(encryptNewPassword);
        updateSysUser.setUpdateTime(new Date());
        return updateById(updateSysUser);
    }

    @Override
    public boolean updateSysUserHead(Long id, String headPath) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setHead(headPath);
        return updateById(sysUser);
    }

    @Override
    public boolean resetPassword(ResetPasswordParam resetPasswordParam) throws Exception {
        String newPassword = resetPasswordParam.getNewPassword();
        String confirmPassword = resetPasswordParam.getConfirmPassword();
        if (!newPassword.equals(confirmPassword)) {
            throw new BusinessException("两次输入的密码不一致");
        }
        // 判断用户是否可修改
        SysUser sysUser = getById(resetPasswordParam.getUserId());
        if (sysUser == null) {
            throw new BusinessException("用户不存在");
        }
        if (StateEnum.DISABLE.getCode().equals(sysUser.getState())) {
            throw new BusinessException("用户已禁用");
        }
        // 密码加密处理
        String salt = sysUser.getSalt();
        // 新密码加密
        String encryptNewPassword = PasswordUtil.encrypt(newPassword, salt);

        // 修改密码
        SysUser updateSysUser = new SysUser();
        updateSysUser.setId(sysUser.getId());
        updateSysUser.setPassword(encryptNewPassword);
        updateSysUser.setUpdateTime(new Date());
        return updateById(updateSysUser);
    }
}
