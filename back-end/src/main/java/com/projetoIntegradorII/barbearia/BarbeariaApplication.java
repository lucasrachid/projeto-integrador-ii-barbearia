package com.projetoIntegradorII.barbearia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication(scanBasePackages = {"com.projetoIntegradorII.barbearia"}, exclude = {SecurityAutoConfiguration.class })
@RequiredArgsConstructor
@Slf4j
@EnableSwagger2
public class BarbeariaApplication {

	public static void main(String[] args) {

		SpringApplication.run(BarbeariaApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projeto-integrador-ii-barbearia");
		EntityManager em = emf.createEntityManager();
	}


}
