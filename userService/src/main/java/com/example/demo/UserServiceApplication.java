package com.example.demo;

import java.io.IOException;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.catalina.connector.Connector;


//import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.api.UserApi;
import com.example.demo.config.TomcatConfig;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.mapper.entity.Example;

/**
 * @author dgm
 * @desc SpringCloud示例工程 -- 用户服务
 * @date
 */

@EnableConfigurationProperties
@ComponentScan
@ServletComponentScan
@EnableWebMvc
@EnableSwagger2
@MapperScan(value  = "com.example.demo.mapper")
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);  

	@Autowired
	private TomcatConfig tomcatConfig;
		
	public static void main(String[] args) throws IOException {
		String bdrack = System.getenv("BDRACK");
		logger.info("配置文件："+(bdrack!=null?((bdrack.endsWith("/")?bdrack+"conf/config.properties":bdrack+"/conf/config.properties")):"conf/config.properties"));
		System.setProperty("spring.config.location",bdrack!=null?((bdrack.endsWith("/")?bdrack+"conf/config.properties":bdrack+"/conf/config.properties")):"conf/config.properties");
		logger.info("获取配置文件："+System.getProperty("spring.config.location"));
		ApplicationContext ctx = SpringApplication.run(UserServiceApplication.class, args);
		
		//Springboot2.3(放弃1版本)启动后，不放心的话可以测试数据库是否连接正常
		UserService userService = ctx.getBean(UserService.class);
		User user = userService.findByUsername("dongguangming");
		if (null != user) {
			logger.info("数据库连接正常，从用户表取用户名是donggguangming的数据,用户："+user);
		} 
	}

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		//factory.setPort(Integer.parseInt(port));
		//logger.info("kaishi999999999999999-" + tomcatConfig.getPort());
		//logger.info("kaishi999999999999999-" + tomcatConfig.getConnectionTimeout());
		//logger.info("kaishi999999999999999-" + tomcatConfig.getAcceptorThreadCount());
		//logger.info("kaishi999999999999999-" + tomcatConfig.getLocation());
	}
	
	@Bean
    public ServletWebServerFactory servletContainer() {
    	logger.info("222-" + tomcatConfig.getPort());

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(new GwsTomcatConnectionCustomizer());
        return tomcat;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
    	logger.info("4444-" + tomcatConfig.getPort());

    	MultipartConfigFactory factory = new MultipartConfigFactory();
       
        factory.setLocation(tomcatConfig.getLocation());
        //  单个数据大小
        factory.setMaxFileSize(tomcatConfig.getMaxFileSize()); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(tomcatConfig.getMaxRequestSize());
        factory.setFileSizeThreshold(tomcatConfig.getFileSizeThreshold());
        return factory.createMultipartConfig();
    }

    /**
     *
     * 默认http连接
     * @version
     *
     */
    public class GwsTomcatConnectionCustomizer implements TomcatConnectorCustomizer {

        public GwsTomcatConnectionCustomizer() {
        }

        @Override
        public void customize(Connector connector) {
        	logger.info("666--------port:"+Integer.valueOf(tomcatConfig.getPort()));
        	logger.info("666-" + tomcatConfig.getPort());
    		logger.info("666-" + tomcatConfig.getConnectionTimeout());
    		logger.info("666-" + tomcatConfig.getAcceptorThreadCount());
    		logger.info("666-" + tomcatConfig.getLocation());
            connector.setPort(Integer.valueOf(tomcatConfig.getPort()));
            connector.setAttribute("connectionTimeout", tomcatConfig.getConnectionTimeout());
            connector.setAttribute("acceptorThreadCount", tomcatConfig.getAcceptorThreadCount());
            connector.setAttribute("minSpareThreads", tomcatConfig.getMinSpareThreads());
            connector.setAttribute("maxSpareThreads", tomcatConfig.getMaxSpareThreads());
            connector.setAttribute("maxThreads", tomcatConfig.getMaxThreads());
            connector.setAttribute("maxConnections", tomcatConfig.getMaxConnections());
            connector.setAttribute("protocol", tomcatConfig.getProtocol());
            connector.setAttribute("redirectPort", "redirectPort");
            connector.setAttribute("compression", "compression");
        }
    }
}
