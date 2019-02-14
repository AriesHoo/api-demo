package com.aries.api.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: AriesHoo on 2019/2/12 10:41
 * @E-Mail: AriesHoo@126.com
 * @Function: Swagger配置文件
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String VERSION = "1.0.0";
    public static final String AUTHOR = "AriesHoo";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.groupName("基础模块")
                .select()
                //加了ApiOperation注解的方法，生成接口文档--接口类文件包即controller位置
                .apis(RequestHandlerSelectors.basePackage("com.aries.api.demo.controller"))
                //可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .enableUrlTemplating(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档的标题
                .title("API文档")
                //设置文档的描述
                .description("测试项目API文档")
                //设置文档的版本信息
                .version(VERSION)
                //作者信息
                .contact(new Contact(AUTHOR, "https://github.com/AriesHoo", "AriesHoo@126.com"))
                .build();
    }

}
