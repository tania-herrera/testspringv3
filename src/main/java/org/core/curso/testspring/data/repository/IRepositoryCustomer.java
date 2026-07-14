package org.core.curso.testspring.data.repository;

import java.util.List;

import org.core.curso.testspring.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCustomer extends JpaRepository<Customer, Integer> {

	public List<Customer> findByName(String name);
	
}
