package com.nhnacademy.codequestreview.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("review-api")
                .version("1.0")
                .description("review-api swagger-ui 화면입니다."));
    }

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/**"};
        String[] packagesToScan = {"com.nhnacademy.codequestreview"};
        return GroupedOpenApi.builder().group("springdoc-openapi")
            .pathsToMatch(paths)
            .packagesToScan(packagesToScan)
            .build();
    }
}