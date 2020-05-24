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

package com.example.rbac.bootstrap.config;

import com.alibaba.fastjson.JSON;
import com.example.rbac.config.properties.SpringBootPlusFilterProperties;
import com.example.rbac.config.properties.SpringBootPlusInterceptorProperties;
import com.example.rbac.config.properties.SpringBootPlusProperties;
import com.example.rbac.framework.core.filter.RequestDetailFilter;
import com.example.rbac.framework.core.interceptor.PermissionInterceptor;
import com.example.rbac.framework.core.xss.XssFilter;
import com.example.rbac.framework.util.IniUtil;
import com.example.rbac.system.interceptor.DownloadInterceptor;
import com.example.rbac.system.interceptor.ResourceInterceptor;
import com.example.rbac.system.interceptor.UploadInterceptor;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

import java.util.Map;

/**
 * WebMvc配置
 *
 * @author geekidea
 * @date 2018-11-08
 */
@Slf4j
@Configuration
public class SpringBootPlusWebMvcConfig implements WebMvcConfigurer {

  	private static final Logger log = LoggerFactory.getLogger(SpringBootPlusWebMvcConfig.class);  

    /**
     * spring-boot-plus配置属性
     */
    @Autowired
    private SpringBootPlusProperties springBootPlusProperties;

    /**
     * Filter配置
     */
    private SpringBootPlusFilterProperties filterConfig;

    /**
     * 拦截器配置
     */
    private SpringBootPlusInterceptorProperties interceptorConfig;

    /**
     * RequestDetailFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean requestDetailFilter() {
        SpringBootPlusFilterProperties.FilterConfig requestFilterConfig = filterConfig.getRequest();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestDetailFilter());
        filterRegistrationBean.setEnabled(requestFilterConfig.isEnable());
        filterRegistrationBean.addUrlPatterns(requestFilterConfig.getUrlPatterns());
        filterRegistrationBean.setOrder(requestFilterConfig.getOrder());
        filterRegistrationBean.setAsyncSupported(requestFilterConfig.isAsync());
        return filterRegistrationBean;
    }

    /**
     * XssFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilter() {
        SpringBootPlusFilterProperties.FilterConfig xssFilterConfig = filterConfig.getXss();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setEnabled(xssFilterConfig.isEnable());
        filterRegistrationBean.addUrlPatterns(xssFilterConfig.getUrlPatterns());
        filterRegistrationBean.setOrder(xssFilterConfig.getOrder());
        filterRegistrationBean.setAsyncSupported(xssFilterConfig.isAsync());
        return filterRegistrationBean;
    }


    /**
     * 自定义权限拦截器
     *
     * @return
     */
    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    /**
     * 上传拦截器
     *
     * @return
     */
    @Bean
    public UploadInterceptor uploadInterceptor() {
        return new UploadInterceptor();
    }

    /**
     * 资源拦截器
     *
     * @return
     */
    @Bean
    public ResourceInterceptor resourceInterceptor() {
        return new ResourceInterceptor();
    }

    /**
     * 下载拦截器
     *
     * @return
     */
    @Bean
    public DownloadInterceptor downloadInterceptor() {
        return new DownloadInterceptor();
    }


    @PostConstruct
    public void init() {
        filterConfig = springBootPlusProperties.getFilter();
        interceptorConfig = springBootPlusProperties.getInterceptor();
        // 打印SpringBootPlusProperties配置信息
        log.debug("SpringBootPlusProperties：{}", JSON.toJSONString(springBootPlusProperties));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 上传拦截器
        if (interceptorConfig.getUpload().isEnable()) {
            registry.addInterceptor(uploadInterceptor())
                    .addPathPatterns(interceptorConfig.getUpload().getIncludePaths());
        }

        // 资源拦截器注册
        if (interceptorConfig.getResource().isEnable()) {
            registry.addInterceptor(resourceInterceptor())
                    .addPathPatterns(interceptorConfig.getResource().getIncludePaths());
        }

        // 下载拦截器注册
        if (interceptorConfig.getDownload().isEnable()) {
            registry.addInterceptor(downloadInterceptor())
                    .addPathPatterns(interceptorConfig.getDownload().getIncludePaths());
        }

        // 自定义权限拦截器注册
        if (interceptorConfig.getPermission().isEnable()) {
            registry.addInterceptor(permissionInterceptor())
                    .addPathPatterns(interceptorConfig.getPermission().getIncludePaths())
                    .excludePathPatterns(interceptorConfig.getPermission().getExcludePaths());
        }

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置项目静态资源访问
        String resourceHandlers = springBootPlusProperties.getResourceHandlers();
        if (StringUtils.isNotBlank(resourceHandlers)) {
            Map<String, String> map = IniUtil.parseIni(resourceHandlers);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String pathPatterns = entry.getKey();
                String resourceLocations = entry.getValue();
                registry.addResourceHandler(pathPatterns)
                        .addResourceLocations(resourceLocations);
            }
        }

        // 设置上传文件访问路径
        registry.addResourceHandler(springBootPlusProperties.getResourceAccessPatterns())
                .addResourceLocations("file:" + springBootPlusProperties.getUploadPath());
    }

}
