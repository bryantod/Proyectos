package com.backend.curso.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI api(){
        return new OpenAPI();
    }
}
