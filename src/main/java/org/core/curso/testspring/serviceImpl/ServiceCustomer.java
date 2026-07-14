package org.core.curso.testspring.serviceImpl;

import java.util.List;

import org.core.curso.testspring.data.model.Customer;
import org.core.curso.testspring.data.repository.IRepositoryCustomer;
import org.core.curso.testspring.service.IServiceCustomer;

public class ServiceCustomer implements IServiceCustomer {
	private IRepositoryCustomer repo;

	@Override
	public List<Customer> findAll() {
		return repo.findAll();
	}

	@Override
	public void showAll() {
		for (Customer customer : this.findAll()) {
			System.out.println("\t" + customer);
		}
	}

	@Override
	public void showTotalPurchasesAvg() {
		Double sumTotalPurchases = 0d;
		for (Customer customer : this.findAll()) {
			//sumTotalPurchases = sumTotalPurchases + customer.getTotalPurchases();
			sumTotalPurchases += customer.getTotalPurchases();
		}
		System.out.println("La media de compras por cliente es " 
				+ (sumTotalPurchases / this.findAll().size()));
	}

	@Override
	public Long count() {
		return repo.count();
	}

	@Override
	public void insertDataTest() {
		iDAOCustomer.insert(new Customer("Ana", 23, 1000d));
		iDAOCustomer.insert(new Customer("Luis", 24, 2000d));
		iDAOCustomer.insert(new Customer("Isaac", 25, 5000d));		
	}

	@Override
	public void insert(Customer customer) {
		iDAOCustomer.insert(customer);
	}
	
	@Override
	public void update(Customer customer) {
		iDAOCustomer.update(customer);
	}

	@Override
	public void deleteById(Integer id) {
		iDAOCustomer.deleteById(id);	
		// TODO pendiente comprobar si está borrado
	}
	
	@Override
	public void delete(Customer customer) {
		iDAOCustomer.delete(customer);	
		// TODO pendiente comprobar si está borrado
	}

	@Override
	public void deleteAll() {
		iDAOCustomer.deleteAll();
	}

	@Override
	public void truncate() {
		iDAOCustomer.truncate();
	}

}
