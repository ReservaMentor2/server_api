package com.reservamentor.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

  @Value("${reservaMentor.openapi.dev-url}") private String devUrl;
  @Value("${reservaMentor.openapi.prod-url}") private String prodUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development Environment");

    Server prodServer = new Server();
    devServer.setUrl(prodUrl);
    devServer.setDescription("Server URL in Production Environment");

    Contact contact = new Contact();
    contact.setName("ReservaMentor");
    contact.setEmail("email@email.com");
    contact.setUrl("www.google.com");

    License BSD =
        new License().name("BSD").url("https://opensource.org/licenses/bsd");

    Info info = new Info()
                    .title("Reserva Mentor")
                    .version("1.0")
                    .contact(contact)
                    .description("Reserva Mentor")
                    .termsOfService("http://www.google.com")
                    .license(BSD);

    SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .name("JWT Authentication");

    Components components = new Components()
            .addSecuritySchemes("bearerAuth", securityScheme);

    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

    return new OpenAPI()
            .info(info)
            .servers(List.of(prodServer, devServer))
            .addSecurityItem(securityRequirement)
            .components(components);
  }
}