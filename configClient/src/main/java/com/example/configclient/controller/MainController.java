package com.example.configclient.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
 
@RefreshScope
@RestController
public class MainController {
 
    // https://github.com/o7planning/spring-cloud-config-git-repo-example
    // See: app-about-company.properties
    @Value("${text.copyright: Default Copyright}")
    private String copyright;
 
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
 
    @Value("${spring.datasource.url}")
    private String url;
 
    @Value("${spring.datasource.username}")
    private String userName;
 
    @Value("${spring.datasource.password}")
    private String password;
 
    @Autowired
    private DataSource dataSource;
 
    @RequestMapping("/showConfig")
    @ResponseBody
    public String showConfig() {
        String configInfo = "Copy Right: " + copyright //
                + "<br/>spring.datasource.driver-class-name=" + driverClassName //
                + "<br/>spring.datasource.url=" + url //
                + "<br/>spring.datasource.username=" + userName //
                + "<br/>spring.datasource.password=" + password;
 
        return configInfo;
    }
 
    @RequestMapping("/pingDataSource")
    @ResponseBody
    public String pingDataSource() {
        try {
        	HikariConfig config = new HikariConfig();
        	config.setJdbcUrl(url);
        	config.setDriverClassName(driverClassName);
        	config.setUsername(userName);
        	config.setPassword(password);
        	config.addDataSourceProperty("cachePrepStmts", "true");
        	config.addDataSourceProperty("prepStmtCacheSize", "250");
        	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        	HikariDataSource ds = new HikariDataSource(config);
            return ds.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
 
}