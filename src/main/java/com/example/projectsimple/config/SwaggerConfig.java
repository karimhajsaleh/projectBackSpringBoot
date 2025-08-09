package com.example.projectsimple.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Simple API")
                        .version("1.0")
                        .description("API pour la gestion des utilisateurs et articles")
                        .contact(new Contact()
                                .name("Karim")
                                .email("k.hejsalahah@gmail.com")));
    }
}