package org.core.curso.testspring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Customer;
import org.core.curso.testspring.data.repository.ICustomerRepository;
import org.core.curso.testspring.service.ICustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
	
	private final ICustomerRepository repo;

	public CustomerService(ICustomerRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<Customer> findAll() {
		return repo.findAll();
	}

	@Override
	public void showList(List<Customer> customerList) {
//		ALTERNATIVA 1: recorrer la colección con un for each tradicional
//		for (Customer customer : customerList) {
//			System.out.println("\t" + customer);
//		}
//		
//		ALTERNATIVA 2: crear stream intermedio y consumirlo con un forEach
//		Stream<Customer> myStream = customerList.stream();
//		myStream.forEach(customer -> System.out.println("\t" + customer));
//		
//		ALTERNATIVA 3: sacar stream directamente de la colección y consumirlo con un forEach
//		y expresión Lambda
//		customerList.stream().forEach(customer -> System.out.println("\t" + customer));
//		
//		ALTERNATIVA 4: sacar stream directamente de la colección y consumirlo con un forEach
//		con una Referencia a Método - Method Reference (::)
		customerList.stream()
			.forEach(System.out::println);
	}
	
	@Override
	public void showCustomer(Optional<Customer> customerOpt) {
//		if (customerOpt.isPresent()) {
//			System.out.println("\t" + customerOpt.get());
//		}
//		else {
//			System.out.println("\tNo existe el id dado");
//		}
//		System.out.println("\t" + 
//				(customerOpt.isPresent()
//						? customerOpt.get() 
//						: "No existe el id dado")
//		);
		System.out.println("\t" + (customerOpt.isPresent() ? customerOpt.get() : "No existe el id dado"));
	}
	
	@Override
	public void showAll() {
//		for (Customer customer : this.findAll()) {
//			System.out.println("\t" + customer);
//		}
		this.findAll().stream()
			.forEach(c -> System.out.println("\t" + c));
	}

	@Override
	public void showTotalPurchasesAvg() {
		Double sumTotalPurchases = 0d;
		for (Customer customer : this.findAll()) {
			//sumTotalPurchases = sumTotalPurchases + customer.getTotalPurchases();
			sumTotalPurchases += customer.getTotalPurchases();
		}
		if (this.findAll().size() == 0) {
			System.out.println("No se puede calcular la media: no hay ningún customer!");
		}
		else {
			System.out.println("La media de compras por cliente es " 
				+ (sumTotalPurchases / this.findAll().size()));
		}
	}

	@Override
	public void showTotalPurchasesAvgFromStream() {
		// Stream con reducción al valor medio (con mapToInt y average):
		this.findAll().stream()
			.mapToInt(Customer::getAge)
			.average()
			.ifPresentOrElse(
				avg -> System.out.println("\nLa media es: " + avg),
				() -> System.out.println("\nLa media no se puede calcular: lista vacía"));
	}

	@Override
	public Long count() {
		return repo.count();
	}

	@Override
	public void saveDataTest() {
		repo.save(new Customer("Ana", 23, 1000d, "12345678"));
		repo.save(new Customer("Luis", 24, 2000d, "87654321"));
		repo.save(new Customer("Isaac", 25, 5000d, "56781234"));		
	}

	@Override
	public void save(Customer customer) {
		repo.save(customer);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	@Override
	public void delete(Customer customer) {
		repo.delete(customer);	
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public void truncate() {
		repo.truncate();
	}

	@Override
	public Boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public List<Customer> findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Customer> findByAgeBetween(Integer minAge, Integer maxAge) {
		return repo.findByAgeBetween(minAge, maxAge);
	}

	@Override
	public List<Customer> findByNameAndAge(String name, Integer age) {
		return repo.findByNameAndAge(name, age);
	}

}
