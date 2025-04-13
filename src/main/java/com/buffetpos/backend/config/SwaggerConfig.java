package com.buffetpos.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    final String bearerScheme = "bearerAuth";
    final String accessCodeScheme = "accessCode";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("BuffetPOS API")
                        .version("1.0")
                        .description("API documentation for BuffetPOS"))
                .addTagsItem(new Tag().name("General").description("General endpoints"))
                .addTagsItem(new Tag().name("Authentication").description("Authentication endpoints"))
                .addTagsItem(new Tag().name("Category Manager").description("Category endpoints"))
                .addTagsItem(new Tag().name("Menu Manager").description("Menu endpoints"))
                .addSecurityItem(new SecurityRequirement().addList(bearerScheme))
                .addSecurityItem(new SecurityRequirement().addList(accessCodeScheme))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(bearerScheme, new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                        .addSecuritySchemes(accessCodeScheme, new SecurityScheme()
                                .name("AccessCode")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)));
    }
}
