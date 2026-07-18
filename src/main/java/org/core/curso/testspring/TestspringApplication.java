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
		//
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1L, "Ana", 23, 1000d, "12345678"));
		customerList.add(new Customer(2L, "Luis", 24, 2000d, "87654321"));
		customerList.add(new Customer(3L, "Isaac", 25, 5000d, "56781234"));	
		//
		// Stream con filtrado y mapeado:
		customerList.stream()
			.peek(c -> System.out.println("\n\t" + c))
			.filter(c -> c.getAge() > 23)
			.peek(c -> System.out.println("\tFiltered: " + c))
			.map(c -> c.getName() + ": " + c.getAge())
			.forEach(c -> System.out.println("\tMapped --> " + c));
		//  
		// Stream con reducción a una suma (con map y reduce):
		customerList.stream()
			.map(Customer::getAge)
			.reduce(Integer::sum)
			.ifPresentOrElse(
				s -> System.out.println("\nLa suma es: " + s),
				() -> System.out.println("\nLa suma no se puede calcular: lista vacía"));
		//  
		// Stream con reducción al valor máximo (con mapToInt y max):
		customerList.stream()
			.mapToInt(Customer::getAge)
			.max()
			.ifPresentOrElse(
				s -> System.out.println("\nEl máximo es: " + s),
				() -> System.out.println("\nEl máximo no se puede calcular: lista vacía"));
		//  
		// Stream con reducción al valor medio (con mapToInt y average):
		customerList.stream()
			.mapToInt(Customer::getAge)
			.average()
			.ifPresentOrElse(
				avg -> System.out.println("\nLa media 1 es: " + avg),
				() -> System.out.println("\nLa media 1 no se puede calcular: lista vacía"));
		//  
		// Stream con reducción al valor medio (con mapToInt y average)
		// con una lista vacía para probar ifPresenteOrElse:
		new ArrayList<Integer>().stream()
			.mapToInt(Integer::intValue)
			.average()
			.ifPresentOrElse(
				avg -> System.out.println("\nLa media 2 es: " + avg),
				() -> System.out.println("\nLa media 2 no se puede calcular: lista vacía"));
		//  
		// Stream con reducción al valor medio (con mapToInt y average)
		// para guardarlo en una variable en lugar de mostrarlo:
		Double media = customerList.stream()
			.mapToInt(Customer::getAge)
			.average()
			.orElse(0D);
        System.out.println("\nLa media 3 guardada es: " + media);
		//  
		// Stream con reducción al valor medio (con mapToInt y average)
		// para guardarlo en una variable en lugar de mostrarlo,
		// con lanzamiento y captura de excepción en caso de división por 0:
		try {
			Double miMedia = customerList.stream()
					.mapToInt(Customer::getAge)
					.average()
					.orElseThrow(() -> new ArithmeticException(
							"No se puede calcular la media 4: división por cero (lista vacía)"));
			// Esta línea solo se ejecutará si la lista NO está vacía
            System.out.println("\nLa media 4 guardada es: " + miMedia);
		} catch (ArithmeticException e) {
            // Capturamos y manejamos el error
            System.out.println("Error detectado en media 4: " + e.getMessage());			
		}
		//  
		// Stream con reducción al valor medio (con mapToInt y average)
		// para guardarlo en una variable en lugar de mostrarlo,
		// con lanzamiento y captura de excepción en caso de división por 0
		// con una lista vacía:
		try {
			Double miMedia = new ArrayList<Integer>().stream()
					.mapToInt(Integer::intValue)
					.average()
					.orElseThrow(() -> new ArithmeticException("No se puede calcular la media 5: división por cero (lista vacía)"));
			// Esta línea solo se ejecutará si la lista NO está vacía
			System.out.println("\nLa media 5  es: " + miMedia);
		} catch (ArithmeticException e) {
			// Capturamos y manejamos el error
			System.out.println("\nError detectado en media 5: " + e.getMessage());			
		}
		//
	}
	

}
