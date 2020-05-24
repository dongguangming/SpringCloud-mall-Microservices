package com.example.demo.api;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.User;


/**
 * 暂时又不需要（需求变更）
 * @author dongguangming
 *
 */
@RestController
@RequestMapping("")
public class BaseAPI {
    
	public Logger logger = LoggerFactory.getLogger(this.getClass());  

	@Autowired
	public  HttpServletRequest request;
	
	 @Autowired
	 private DiscoveryClient discoveryClient;
	 
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	 @CrossOrigin
	 public JSONObject check(User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession session) {
			logger.info("用户登录(" + user.getUsername() + "," + user.getPassword() + ")");

		    logger.info("健康检查，查看tomcat9  是否也已启动 ");
			JSONObject jobj = new JSONObject();
			jobj.put("result", "success");
			jobj.put("msg", "恭喜tomcat9 也已启动");
			return jobj;
	 }
	 
	 @ResponseBody
	    @RequestMapping(value = "/home", method = RequestMethod.GET)
	    public String home() {
	 
	        return "<a href='showAllServiceIds'>Show All Service Ids</a>";
	    }
	 
	    @ResponseBody
	    @RequestMapping(value = "/showAllServiceIds", method = RequestMethod.GET)
	    public String showAllServiceIds() {
	 
	        List<String> serviceIds = this.discoveryClient.getServices();
	 
	        if (serviceIds == null || serviceIds.isEmpty()) {
	            return "No services found!";
	        }
	        String html = "<h3>Service Ids:</h3>";
	        for (String serviceId : serviceIds) {
	            html += "<br><a href='showService?serviceId=" + serviceId + "'>" + serviceId + "</a>";
	        }
	        return html;
	    }
	 
	    @ResponseBody
	    @RequestMapping(value = "/showService", method = RequestMethod.GET)
	    public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {
	 
	        // (Need!!) eureka.client.fetchRegistry=true
	        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
	 
	        if (instances == null || instances.isEmpty()) {
	            return "No instances for service: " + serviceId;
	        }
	        String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";
	 
	        for (ServiceInstance serviceInstance : instances) {
	            html += "<h3>Instance: " + serviceInstance.getUri() + "</h3>";
	            html += "Host: " + serviceInstance.getHost() + "<br>";
	            html += "Port: " + serviceInstance.getPort() + "<br>";
	        }
	 
	        return html;
	    }
	 
	    // A REST method, To call from another service.
	    // See in Lesson "Load Balancing with Ribbon".
	    @ResponseBody
	    @RequestMapping(value = "/hello", method = RequestMethod.GET)
	    public String hello() {
	 
	        return "<html>Hello from USER-SERVICE</html>";
	    }
	    
}
