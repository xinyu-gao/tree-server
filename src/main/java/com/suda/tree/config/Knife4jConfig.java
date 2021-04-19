package com.suda.tree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 API文档的配置
 */
@Configuration
@EnableSwagger2
public class Knife4jConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("古树名木系统后端开发接口文档")
                        .version("1.0")
                        .build())
                .groupName("1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.suda.tree.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}