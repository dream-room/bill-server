package com.dream.room.bill.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/24.
 */
@Profile("dev")
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts( Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(
                        new ApiKey("Authorization","Authorization","header",Collections.singletonList(
                                new VendorExtension() {
                                    @Override
                                    public String getName() {
                                        return "JWT令牌";
                                    }
                                    @Override
                                    public Object getValue() {
                                        return "Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eNqqViouTVKyUkpMyc3MU9JRyiwuBvKSMnNydItTi8pSi0BiiSVKVoamRkbm5gYGpkY6SomlKTBFyTmZqXklSrUAAAAA__8.QvY4naA4huMqBS_yUduieqa9LcRT2z37mdJuiQONmio";
                                    }
                                }
                        ))
                ))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dream.room.bill.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /*@Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }*/

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bill Server APIs Doc")
                .description("接口文档")
                .contact(new Contact("MrTT","https://github.com/dream-room/bill-server","jiang.taojie@foxmail.com"))
                .version("0.1")
                .license("Apache License 2.0")
                .licenseUrl("https://github.com/dream-room/bill-server/blob/master/LICENSE")
                //.extensions()
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
    }

}
