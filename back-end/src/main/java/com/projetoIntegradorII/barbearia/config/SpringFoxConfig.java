package com.projetoIntegradorII.barbearia.config;

import io.swagger.annotations.Api;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .tags(
                new Tag("Test", "Rotas de teste."),
                new Tag("Authorization", "Gerar o token de autenticação.")
            )
            .apiInfo(apiInfo())
            .securityContexts(List.of(securityContexts()))
            .securitySchemes(List.of(securitySchemes()))
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Projeto Integrador II - Barbearia")
            .description("Documentação da API com o Swagger")
            .version("0.0.1-SNAPSHOT")
            .license("MIT")
            .licenseUrl("https://opensource.org/licenses/MIT")
            .build();
    }

    private SecurityScheme securitySchemes() {
        return HttpAuthenticationScheme
            .JWT_BEARER_BUILDER
            .name(HttpHeaders.AUTHORIZATION)
            .scheme("bearer")
            .description("Bearer Token")
            //  After that, just add token that will do , Will it be automatic ”Bearer “ On the prefix
            .bearerFormat("Bearer ")
            .build();
    }
    private SecurityContext securityContexts() {
        return SecurityContext.builder()
            .securityReferences(
                List.of(
                    SecurityReference.builder()
                        .reference(HttpHeaders.AUTHORIZATION)
                        .scopes(
                            new AuthorizationScope[] {
                                new AuthorizationScope("global", "global")
                            })
                        .build()))
            .build();
    }

}
