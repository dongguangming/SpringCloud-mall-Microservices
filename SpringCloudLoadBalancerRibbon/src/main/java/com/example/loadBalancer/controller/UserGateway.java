package com.example.loadBalancer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
 
/**
 * 
 * @author dgm
 * @describe "用户网关"
 * @date 2020年5月21日
 */
@RestController
public class UserGateway {
 
	public Logger logger = LoggerFactory.getLogger(this.getClass());  

    @Autowired
    private DiscoveryClient discoveryClient;
 
    @Autowired
    private LoadBalancerClient loadBalancer;
 
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(@RequestParam(value = "serviceId", required = false , defaultValue = "USER-SERVICE") String serviceId) {
 
        return "<a href='testInvokeUserService?serviceId=" + serviceId + "'>testInvokeUserService: " + serviceId + "</a>";

    }
 
    @ResponseBody
    @RequestMapping(value = "/testInvokeUserService", method = RequestMethod.GET)
    public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {
 
    	logger.info("Service id = "+serviceId);
        //String serviceId = serviceId;//.toLowerCase();
 
        // (Need!!) eureka.client.fetchRegistry=true
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
 
        if (instances == null || instances.isEmpty()) {
            return "No instances for service: " + serviceId;
        }
        String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";
 
        for (ServiceInstance serviceInstance : instances) {
            html += "<h3>Instance :" + serviceInstance.getUri() + "</h3>";
        }
 
        // Create a RestTemplate.
        RestTemplate restTemplate = new RestTemplate();
 
        html += "<br><h4>Call /hello of service: " + serviceId + "</h4>";
 
        try {
            // May be throw IllegalStateException (No instances available)
            ServiceInstance serviceInstance = this.loadBalancer.choose(serviceId);
 
            html += "<br>===> Load Balancer choose: " + serviceInstance.getUri();
 
            String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello";
 
            html += "<br>Make a Call: " + url;
            html += "<br>";
 
            String result = restTemplate.getForObject(url, String.class);
 
            html += "<br>Result: " + result;
        } catch (IllegalStateException e) {
            html += "<br>loadBalancer.choose ERROR: " + e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            html += "<br>Other ERROR: " + e.getMessage();
            e.printStackTrace();
        }
        return html;
    }
 
}
