package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
 
@EnableEurekaServer
@SpringBootApplication
/**
 * 
 * @author dgm
 * @describe "注册服务器"
 * @date 2020年5月19日
 */
public class SpringCloudServiceRegistrationEurekaServerApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServiceRegistrationEurekaServerApplication.class, args);
    }
     
}