package com.reservamentor.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${reservaMentor.openapi.url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        Contact contact = new Contact();
        contact.setName("ReservaMentor");
        contact.setEmail("email@email.com");
        contact.setUrl("www.google.com");

        License BSD = new License().name("BSD").url("https://opensource.org/licenses/bsd");

        Info info = new Info()
                .title("Reserva Mentor")
                .version("1.0")
                .contact(contact)
                .description("Reserva Mentor")
                .termsOfService("http://www.google.com")
                .license(BSD);

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);
    }
}
