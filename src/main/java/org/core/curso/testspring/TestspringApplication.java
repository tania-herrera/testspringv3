package org.core.curso.testspring;

import org.core.curso.testspring.service.ITestStream;
import org.core.curso.testspring.serviceImpl.TestStreamImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestspringApplication {

	public static void main(String[] args) {
		
		initTestStream();
		
		SpringApplication.run(TestspringApplication.class, args);
	}

	private static void initTestStream() {
		ITestStream testStream = new TestStreamImpl();
		testStream.TestStream1();
	}

}
