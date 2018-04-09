package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import demo.example.demo.controllers.Confirmation;

@SpringBootApplication
@ComponentScan({"repositories","com.example.demo.models","com.example.demo.controllers","com.example.demo.service"})
@EntityScan("com.example.demo.models.Payment")
@EnableMongoRepositories("repositories")
@ComponentScan(basePackageClasses = Confirmation.class)

public class PaymentsApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
		logger.debug("--Application Started--");
	}
}
