package org.core.curso.testspring.service;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Customer;

public interface IServiceCustomer {

	public void truncate();
	public void deleteAll();
	public void deleteById(Integer id);
	public void delete(Customer customer);

	public void save(Customer customer);
	public void saveDataTest();
	
	public Boolean existsById(Integer id);
	public Long count();
	
	public Optional<Customer> findById(Integer id);
	public List<Customer> findByName(String name);
	public List<Customer> findByAgeBetween(Integer minAge, Integer maxAge);
	public List<Customer> findByNameAndAge(String name, Integer age);
	public List<Customer> findAll();
	
	public void showAll();
	public void showTotalPurchasesAvg();
	public void showList(List<Customer> customerList);
	public void showCustomer(Optional<Customer> customerOpt);	
	
}
