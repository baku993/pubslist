package com.alco.pubslist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PubslistApplication {
	public static void main(String[] args) {

		SpringApplication.run(PubslistApplication.class, args);
	}
}
