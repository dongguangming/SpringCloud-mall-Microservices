package com.example.loadBalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.example.loadBalancer.config.RibbonConfiguration;
 
@RibbonClient(name = "ping-a-server", configuration = RibbonConfiguration.class)
@EnableEurekaClient
@SpringBootApplication
/**
 * 
 * @author dgm
 * @describe ""
 * @date 2020年5月21日
 */
public class SpringCloudLoadBalancerRibbonApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudLoadBalancerRibbonApplication.class, args);
    }
 
}