package org.core.curso.testspring;

import java.util.ArrayList;
import java.util.List;

import org.core.curso.testspring.config.DataInitializer;
import org.core.curso.testspring.data.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication
public class TestspringApplication {

	public static void main(String[] args) {
				
		SpringApplication.run(TestspringApplication.class, args);
		
		//initTestStream(); // Pruebas con streams.
		
	}

	@Bean
	CommandLineRunner initDatabase (DataInitializer dataInitializer) {
		
		return args -> dataInitializer.initialize();
		
	}
	
	private static void initTestStream() {
//		ITestStream testStream = new TestStreamImpl();
//		testStream.testStream1();
//		testStream.testStream2();
	}
	

}
