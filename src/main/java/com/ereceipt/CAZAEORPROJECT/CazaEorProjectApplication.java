package com.ereceipt.CAZAEORPROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableJpaRepositories
@SpringBootApplication
public class CazaEorProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CazaEorProjectApplication.class, args);
	}

}
