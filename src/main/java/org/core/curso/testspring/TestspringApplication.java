package org.core.curso.testspring;

import org.core.curso.testspring.config.DataInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestspringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
				
		SpringApplication.run(TestspringApplication.class, args);
		
		//initTestStream(); // Pruebas con streams.
		
	}

	@Bean
	CommandLineRunner initDatabase (DataInitializer dataInitializer) {
		
		return args -> dataInitializer.initialize();
		
	}
	
//	private static void initTestStream() {
//		ITestStream testStream = new TestStreamImpl();
//		testStream.testStream1();
//		testStream.testStream2();
//	}
	

}
