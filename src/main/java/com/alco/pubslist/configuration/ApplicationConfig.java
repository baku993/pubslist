package com.alco.pubslist.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@EntityScan("com.alco.pubslist.entities")
@EnableJpaRepositories("com.alco.pubslist.repository")
@ControllerAdvice("com.alco.pubslist.ExceptionHandler")
public class ApplicationConfig {
}
