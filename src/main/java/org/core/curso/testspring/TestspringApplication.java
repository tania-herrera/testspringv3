package org.core.curso.testspring;

import java.util.ArrayList;
import java.util.List;

import org.core.curso.testspring.data.model.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestspringApplication {

	public static void main(String[] args) {
				
		SpringApplication.run(TestspringApplication.class, args);
		
		initTestStream();
	}

	private static void initTestStream() {
//		ITestStream testStream = new TestStreamImpl();
//		testStream.testStream1();
//		testStream.testStream2();
	}
	

}
