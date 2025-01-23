package com.iupi.fintech.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

@Bean
    public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                    .addList("bearer-key"))
            .components(new Components()
                    .addSecuritySchemes("bearer-key",
                            new SecurityScheme()
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")))
            .info(new io.swagger.v3.oas.models.info.Info()
                    .title("API Iupi Fintech")
                    .description("Iupi Fintech app Rest API. Tu mejor forma de invertir")
                    .contact(new io.swagger.v3.oas.models.info.Contact()
                            .name("Backend Team")
                            .email("angeljsdev@gmail.com"))
                    .license(new io.swagger.v3.oas.models.info.License()
                            .name("Apache 2.0")
                            .url("https://iupi-project.io/api/licence")));
}
    }

