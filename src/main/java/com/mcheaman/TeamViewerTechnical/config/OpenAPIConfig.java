package com.mcheaman.TeamViewerTechnical.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${mcheaman.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("mcheaman@yahoo.com");
        contact.setName("Mason Heaman");
        contact.setUrl("https://github.com/mcheaman");

        License apache2License = new License().name("Apache 2.0").url("https://choosealicense.com/licenses/apache-2.0/");

        Info info = new Info()
                .title("Simple E-commerce CRUD API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to model a simple ecommerce website")
                .license(apache2License);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}