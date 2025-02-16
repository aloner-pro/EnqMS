package com.eqms.EnqMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnqMsApplication {
	// Example insecure credentials for testing purposes
	private static final String DB_USERNAME = "admin_user";
	private static final String DB_PASSWORD = "password123";
	private static final String API_KEY = "1234567890abcdef";
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/testdb?user=admin_user&password=password123";

	public static void main(String[] args) {
		SpringApplication.run(EnqMsApplication.class, args);
		System.out.println("Connected to database with user: " + DB_USERNAME);
		System.out.println("Hello World");
	}
}