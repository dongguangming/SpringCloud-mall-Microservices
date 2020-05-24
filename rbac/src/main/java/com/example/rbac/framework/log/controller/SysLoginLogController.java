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

package com.example.rbac.framework.log.controller;

import com.example.rbac.framework.common.api.ApiResult;
import com.example.rbac.framework.common.controller.BaseController;
import com.example.rbac.framework.core.pagination.Paging;
import com.example.rbac.framework.log.annotation.Module;
import com.example.rbac.framework.log.annotation.OperationLog;
import com.example.rbac.framework.log.entity.SysLoginLog;
import com.example.rbac.framework.log.enums.OperationLogType;
import com.example.rbac.framework.log.param.SysLoginLogPageParam;
import com.example.rbac.framework.log.service.SysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统登录日志 控制器
 *
 * @author geekidea
 * @since 2020-03-24
 */
@Slf4j
@RestController
@RequestMapping("/sysLoginLog")
@Module("log")
@Api(value = "系统登录日志API", tags = {"系统登录日志"})
public class SysLoginLogController extends BaseController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 系统登录日志分页列表
     */
    @PostMapping("/getPageList")
    @RequiresPermissions("sys:login:log:page")
    @OperationLog(name = "系统登录日志分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "系统登录日志分页列表", response = SysLoginLog.class)
    public ApiResult<Paging<SysLoginLog>> getSysLoginLogPageList(@Validated @RequestBody SysLoginLogPageParam sysLoginLogPageParam) throws Exception {
        Paging<SysLoginLog> paging = sysLoginLogService.getSysLoginLogPageList(sysLoginLogPageParam);
        return ApiResult.ok(paging);
    }

}

