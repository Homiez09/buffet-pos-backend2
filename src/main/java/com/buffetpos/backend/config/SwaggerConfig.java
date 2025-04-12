package com.buffetpos.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("BuffetPOS API")
                        .version("1.0")
                        .description("API documentation for BuffetPOS"))
                .addTagsItem(new Tag().name("General").description("General endpoints"))
                .addTagsItem(new Tag().name("Authentication").description("Authentication endpoints")
                );
    }
}
