package org.core.curso.testspring.service;

import java.util.List;

import org.core.curso.testspring.data.model.Customer;

public interface IServiceCustomer {

	/*
	public List<Customer> findByName();
	public Optional<Customer> findById();
	public List<Customer> findByAgeBetween(Integer minAge, Integer maxAge);
	 */

	public void update(Customer customer);
	
	public void truncate();
	public void deleteAll();
	public void deleteById(Integer id);
	public void delete(Customer customer);

	public void insert(Customer customer);

	public void insertDataTest();
	
	public Long count();
	
	public List<Customer> findAll();
	public void showAll();
	public void showTotalPurchasesAvg();
	
	
	
	
}
