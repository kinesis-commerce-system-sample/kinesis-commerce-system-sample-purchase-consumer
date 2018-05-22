package com.example.kinesiscommercesystemsample.purchase.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PurchaseConsumerApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PurchaseConsumerApplication.class);
		application.run(args);
	}
}
