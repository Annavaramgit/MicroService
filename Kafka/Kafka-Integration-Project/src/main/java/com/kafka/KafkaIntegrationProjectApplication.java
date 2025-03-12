package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaIntegrationProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaIntegrationProjectApplication.class, args);
		System.out.println("kafka app started !!!  ");
	}

}
