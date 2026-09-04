package org.core.curso.testspring.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Valuation;
import org.core.curso.testspring.data.repository.IValuationRepository;
import org.core.curso.testspring.service.IValuationService;
import org.springframework.stereotype.Service;

@Service
public class ValuationService implements IValuationService {
	
	private final IValuationRepository repo;
	
	public ValuationService(IValuationRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<Valuation> findAll() {
		return repo.findAll();
	}

	@Override
	public void showItem(Optional<Valuation> itemOpt) {
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
		repo.save(new Valuation("MUY MALO"));
		repo.save(new Valuation("MALO"));
		repo.save(new Valuation("REGULAR"));
		repo.save(new Valuation("BUENO"));
		repo.save(new Valuation("MUY BUENO"));
	}

	@Override
	public void save(Valuation valuation) {
		repo.save(valuation);
	}

	@Override
	public void deleteById(Long id) {
		Optional<Valuation> valuationOpt = this.findById(id);
		if ((valuationOpt.isPresent() && (valuationOpt.get().getProductList().size() == 0))) {
			repo.deleteById(id);
		}	
	}
	
	@Override
	public void delete(Valuation valuation) {
		this.deleteById(valuation.getId());
		//repo.delete(valuation);	
	}

	@Override
	public void deleteAll() {
		for(Valuation valuation : this.findAll()) {
			this.delete(valuation);
		}
		//repo.deleteAll();	
	}

	@Override
	public Boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<Valuation> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public void truncate() {
		repo.truncate();
	}

	@Override
	public List<Valuation> findByName(String name) {
		return repo.findByName(name);
	}

}
