package org.core.curso.testspring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Family;
import org.core.curso.testspring.data.model.Product;
import org.core.curso.testspring.data.model.Valuation;
import org.core.curso.testspring.data.repository.IProductRepository;
import org.core.curso.testspring.service.IFamilyService;
import org.core.curso.testspring.service.IProductService;
import org.core.curso.testspring.service.IValuationService;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	
	private final IProductRepository repo;
	private final IValuationService valuationService;
	private final IFamilyService familyService;

	public ProductService(
			IProductRepository repo, 
			IValuationService valuationService, 
			IFamilyService familyService) {
		super();
		this.repo = repo;
		this.valuationService = valuationService;
		this.familyService = familyService;
	}

	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public void showList(List<Product> productList) {
		productList.stream()
			.forEach(System.out::println);
	}
	
	@Override
	public void showItem(Optional<Product> itemOpt) {
		System.out.println("\t" + (itemOpt.isPresent() ? itemOpt.get() : "No existe el id dado"));
	}
	
	@Override
	public void showAll() {
		this.findAll().stream()
			.forEach(c -> System.out.println("\t" + c));
	}

	@Override
	public Long count() {
		return repo.count();
	}

	@Override
	public void saveDataTest() {
		// Web con imágenes de productos:
		// https://mdbootstrap.com/img/new/ecommerce/horizontal/084.jpg
		repo.save(
				Product.builder()
//					.id(1L)
					.name("Ratón")
					.URL("https://mdbootstrap.com/img/new/ecommerce/horizontal/008.jpg")
					.price(500.00)
					.stock(10)
					.discount(0.0)
					.valuation(this.valuationService.findById(2L).get())
					.family(this.familyService.findById(2L).get())
				.build()
		);
		repo.save(
				Product.builder()
//					.id(2L)
					.name("Limones")
					.URL("https://mdbootstrap.com/img/new/ecommerce/horizontal/084.jpg")
					.price(5.00)
					.stock(200)
					.discount(10.0)
					.valuation(this.valuationService.findById(3L).get())
					.family(this.familyService.findById(1L).get())
				.build()
		);
		repo.save(
				Product.builder()
				.family(this.familyService.findById(3L).get())
//				.id(3L)
				.name("Zapatos")
				.URL("https://mdbootstrap.com/img/new/ecommerce/horizontal/089.jpg")
				.price(50.00)
				.stock(100)
				.discount(15.0)
				.valuation(this.valuationService.findById(1L).get())
				.build()
				);
	}

	@Override
	public void save(Product product) {
		repo.save(product);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	@Override
	public void delete(Product product) {
		repo.delete(product);	
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
	public List<Product> findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
		return repo.findByPriceBetween(minPrice, maxPrice);
	}

	@Override
	public List<Product> findByStockLessThan(Integer minStock) {
		return repo.findByStockLessThan(minStock);
	}

	@Override
	public List<Product> findByValuationId(Long valuationId) {
		return repo.findByValuationId(valuationId);
	}

	@Override
	public List<Product> findByFamilyName(String familyName) {
		return repo.findByFamilyName(familyName);
	}


}
