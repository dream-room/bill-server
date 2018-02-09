package com.dream.room.bill.common;

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
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/24.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.globalOperationParameters()
                //.globalResponseMessage(RequestMethod.DELETE,)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dream.room.bill.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bill Server APIs Doc")
                .description("接口文档")
                .contact(new Contact("MrTT","https://github.com/dream-room/bill-server","jiang.taojie@foxmail.com"))
                .version("0.1")
                .license("MIT")
                .licenseUrl("https://github.com/dream-room/bill-server/blob/master/LICENSE")
                //.extensions()
                .build();
    }
}
