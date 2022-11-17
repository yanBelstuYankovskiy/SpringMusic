package com.valko.SpringMusic.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Open-API documentation for SptingMusic")
                                .version("6.6.6")
                                .contact(
                                        new Contact()
                                                .email("korovaabc@gmail.com")
                                                .url("https://vk.com/id340176600")
                                                .name("Valko Sergei")
                                )
                );
    }
}
