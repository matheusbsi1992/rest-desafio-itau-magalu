package br.com.desafio.magalu;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@SpringBootApplication
/*@ComponentScan(basePackages = {"br.*"})
@EntityScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.*"})*/
public class ApiNotificacaoAgendamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiNotificacaoAgendamentoApplication.class, args);
    }

    @Bean
    public OpenAPI customAPI() {
        Server server = new Server();
        server.setUrl("https://localhost:9293");
        server.setDescription("Desafio");

        Contact contact = new Contact();
        contact.email("matheusbsi1992@gmail.com");
        contact.name("Matheus Andrade");

        Info info = new Info()
                .title("Magazine Luiza - Desafio de Programação")
                .version("v1")
                .description("Uso de API para o desafio Magazine Luiza")
                .contact(contact)
                .termsOfService("")
                .license(
                        new License()
                                .name("Apache 2.0")
                                .url(""));

        return new OpenAPI().info(info).servers(List.of(server));
    }

}