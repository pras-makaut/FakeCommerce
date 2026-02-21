package com.example.FakeCommerce;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FakeCommerceApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().load();

        dotenv.entries().forEach((entries) -> System.setProperty(entries.getKey(),entries.getValue()));

        SpringApplication.run(FakeCommerceApplication.class, args);
	}

}
