package org.core.curso.testspring.service;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {

	public void truncate();
	public void deleteAll();
	public void deleteById(Long id);
	public void delete(Product product);

	public void save(Product product);
	public void saveDataTest();
	
	public Boolean existsById(Long id);
	public Long count();
	
	public Optional<Product> findById(Long id);
	public List<Product> findByName(String name);
	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
	public List<Product> findByStockLessThan(Integer minStock);
	public List<Product> findByValuationId(Long valuationId);
	public List<Product> findByFamilyName(String familyName);
	public List<Product> findAll();
	
	public void showAll();
	public void showList(List<Product> productList);
	public void showItem(Optional<Product> itemOpt);
	public Double calculateAveragePrice();
	
}
