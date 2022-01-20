package com.enigma.datamanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Customer Data Management API",
		description = "Customer Data Management API for Documentation",
		version = "1.0"
))
public class DataManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataManagementApplication.class, args);
	}

}
