package org.core.curso.testspring.data.repository;

import java.util.List;

import org.core.curso.testspring.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

	public List<Customer> findByName(String name);
	public List<Customer> findByAgeBetween(Integer minAge, Integer maxAge);
	public List<Customer> findByNameAndAge(String name, Integer age);
	
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE CUSTOMER", nativeQuery = true)
	public void truncate();
	
}
