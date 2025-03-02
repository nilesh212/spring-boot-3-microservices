package com.techie.microservices.order.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI productServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Order Service API")
                        .version("v0.0.1")
                        .description("This is the REST API for Order Service API")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Order Service Wiki Documentation")
                        .url("https://order-service-dummy-url.com/docs"));
    }
}
