package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author dongguangming
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                 //api包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.api"))
                .paths(PathSelectors.any())
                .build();
                //.securitySchemes(securitySchemes())
                //.securityContexts(securityContexts());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		 //页面标题
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                 //描述
                .description("")
                .termsOfServiceUrl("")
                //创建人
                .contact(new Contact("dongguangming", "", ""))
                //版本号
                .version("3.0")
                .build();
    }
}
