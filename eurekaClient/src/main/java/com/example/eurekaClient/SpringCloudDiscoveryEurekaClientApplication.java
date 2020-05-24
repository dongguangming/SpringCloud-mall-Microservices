package com.example.eurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
 
@EnableEurekaClient
@SpringBootApplication
/**
 * 
 * @author dgm
 * @describe "服务发现"
 * @date 2020年5月19日
 */
public class SpringCloudDiscoveryEurekaClientApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDiscoveryEurekaClientApplication.class, args);
    }
     
}