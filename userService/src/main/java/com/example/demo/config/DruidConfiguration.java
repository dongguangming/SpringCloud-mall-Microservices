package com.example.demo.config;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;  
import org.springframework.boot.web.servlet.ServletRegistrationBean;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
  
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Level;
import java.sql.SQLException;  
import javax.sql.DataSource; 
import com.alibaba.druid.pool.DruidDataSource;  
import com.alibaba.druid.support.http.StatViewServlet;  
import com.alibaba.druid.support.http.WebStatFilter;  

/**  
 * 配置druid数据源  
 *  
 */  
@Configuration
@PropertySource("classpath:application.properties")
public class DruidConfiguration {  
      
    private Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);  

    @Autowired
	ConfigurableEnvironment environment;
    
	// 将所有前缀为spring.datasource下的配置项都加载到DataSource中
    @Bean
    public DataSource druidDataSource() {

    	logger.info("db url-----------: "+environment.getProperty("spring.datasource.url"));

        DruidDataSource datasource = new DruidDataSource();
		datasource.setDbType(environment.getProperty("spring.datasource.dbType"));
		datasource.setName(environment.getProperty("spring.datasource.name"));
        datasource.setUrl(environment.getProperty("spring.datasource.url"));
        datasource.setUsername(environment.getProperty("spring.datasource.username"));
        datasource.setPassword(environment.getProperty("spring.datasource.password"));
        datasource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));

        //configuration
        datasource.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.initialSize")));
        datasource.setMinIdle(Integer.parseInt(environment.getProperty("spring.datasource.minIdle")));
        datasource.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.maxActive")));
        datasource.setMaxWait(Long.parseLong(environment.getProperty("spring.datasource.maxWait")));
        datasource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
        datasource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
        datasource.setValidationQuery(environment.getProperty("spring.datasource.validationQuery"));
        datasource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("spring.datasource.testWhileIdle")));
        datasource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnBorrow")));
        datasource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnReturn")));
        datasource.setPoolPreparedStatements(Boolean.parseBoolean(environment.getProperty("spring.datasource.poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(environment.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
        try {
            datasource.setFilters(environment.getProperty("spring.datasource.filters"));
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {}", e);
        }
        datasource.setConnectionProperties(environment.getProperty("spring.datasource.connectionProperties"));

        return datasource;          
    }
    
    @Bean  
    public ServletRegistrationBean druidStatViewServlet() {  
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

    	/** 初始化参数配置，initParams**/
        //白名单
        bean.addInitParameter("allow", "127.0.0.1");//
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //bean.addInitParameter("deny", "192.168.1.110");
        //登录查看信息的账号密码.
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }  
  
    @Bean  
    public FilterRegistrationBean druidWebStatViewFilter() {  
    	FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
    	 //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean; 
    }  

}  
