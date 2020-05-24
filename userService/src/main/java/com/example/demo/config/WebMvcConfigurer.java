package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    //自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        super.addInterceptors(registry);
    }
    
    //自定义参数解析器
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
    //json转换工具
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(fastJsonHttpMessageConverterEx());
        super.configureMessageConverters(converters);
    }

    @Bean
    public FastJsonHttpMessageConverterEx fastJsonHttpMessageConverterEx() {
        return new FastJsonHttpMessageConverterEx();
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
    
    //注册filter
    /*@Bean  
    public FilterRegistrationBean  filterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        TokenAuthorFilter tokenAuthorFilter = new TokenAuthorFilter();  
        registrationBean.setFilter(tokenAuthorFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;  
    } */ 
    
    @Bean
    public CORSInterceptor corsInterceptor() {
        return new CORSInterceptor();
    }
    
    /**
     * ESTful API display in swagger-ui
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
          registry.addResourceHandler("/webjars/**")
                 .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
