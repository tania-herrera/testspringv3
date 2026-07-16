package org.core.curso.testspring;

import java.util.ArrayList;
import java.util.List;

import org.core.curso.testspring.data.model.Customer;
import org.core.curso.testspring.service.IServiceCustomer;
import org.core.curso.testspring.service.ITestStream;
import org.core.curso.testspring.serviceImpl.ServiceCustomer;
import org.core.curso.testspring.serviceImpl.TestStreamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestspringApplication {

	public static void main(String[] args) {
		
		initTestStream();
		
		SpringApplication.run(TestspringApplication.class, args);
	}

	private static void initTestStream() {
//		ITestStream testStream = new TestStreamImpl();
//		testStream.TestStream1();
		//
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("Ana", 23, 1000d, "12345678"));
		customerList.add(new Customer("Luis", 24, 2000d, "87654321"));
		customerList.add(new Customer("Isaac", 25, 5000d, "56781234"));	
		//
		customerList.stream()
			.peek(c -> System.out.println("\n\t" + c))
			.filter(c -> c.getAge() > 23)
			.peek(c -> System.out.println("\tFiltered: " + c))
			.map(c -> c.getName() + ": " + c.getAge())
			.forEach(c -> System.out.println("\tMapped --> " + c));
		}
	

}
