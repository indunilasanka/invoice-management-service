package com.demo.ims.configuration;

import com.demo.ims.model.dto.StatusUpdateRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("^(/ims/v1/).*"))
                .build()
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(
                        StatusUpdateRequest.class)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Invoice Management Service",
                "APIs to alter/expose invoices details",
                "v1",
                "Terms of service",
                new Contact("asanka", "", "waindunilasanka@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
