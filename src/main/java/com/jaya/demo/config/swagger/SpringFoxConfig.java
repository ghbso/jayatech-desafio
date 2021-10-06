package com.jaya.demo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {
    public static final String CURRENCY_EXCHANGE_ENDPOINTS = "Currency exchange endpoints";


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jaya.demo.resources"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder().title("Currency exchange API").build())
                .tags(new Tag(CURRENCY_EXCHANGE_ENDPOINTS, "Endpoints to currency exchange actions for the following currencies: BRL, USD, EUR, JPY."));
    }
}