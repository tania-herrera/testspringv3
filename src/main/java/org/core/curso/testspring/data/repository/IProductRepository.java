package org.core.curso.testspring.data.repository;

import java.util.List;

import org.core.curso.testspring.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByName(String name);
	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
	public List<Product> findByStockLessThan(Integer minStock);
	public List<Product> findByValuationId(Long valuationId);
	public List<Product> findByFamilyName(String familyName);
	
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE PRODUCT", nativeQuery = true)
	public void truncate();
	
}
