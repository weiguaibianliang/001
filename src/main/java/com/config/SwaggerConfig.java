package com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig  implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public Docket createRestApi(){

        //接口文档描述信息
        ApiInfo apiInfoBuilder = new ApiInfoBuilder()
                .title("shirtProcess apis")
                .description("shirtProcess apis Swagger API 服务")
                .termsOfServiceUrl("http://swagger.io/")
                //开发文档的联系人信息
                .contact(new Contact("huZheng", "", "1821221063@qq.com"))
                .version("1.0-SNAPSHOT")
                .build();

//        ParameterBuilder restApiParam = new ParameterBuilder();
//        List<Parameter> parameterList = new ArrayList<>();
//        restApiParam.name("Authorization").description("认证token").defaultValue("{{token}}")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build(); //header中的ticket参数非必填，传空也可以
//        parameterList.add(restApiParam.build());    //根据每个方法名也知道当前方法在设置什么参数

        //Docket容器
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfoBuilder)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
}
