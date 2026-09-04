package org.core.curso.testspring.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.core.curso.testspring.data.model.Family;
import org.core.curso.testspring.data.repository.IFamilyRepository;
import org.core.curso.testspring.service.IFamilyService;
import org.springframework.stereotype.Service;

@Service
public class FamilyService implements IFamilyService {
	
	private final IFamilyRepository repo;
	
	public FamilyService(IFamilyRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public List<Family> findAll() {
		return repo.findAll();
	}

	@Override
	public void showItem(Optional<Family> itemOpt) {
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
		repo.save(new Family("ALIMENTACION"));
		repo.save(new Family("ELECTRONICA"));
		repo.save(new Family("TEXTIL"));
		repo.save(new Family("JOYERIA"));
		repo.save(new Family("PERFUMERIA"));
	}

	@Override
	public void save(Family family) {
		repo.save(family);
	}

	@Override
	public void deleteById(Long id) {
		Optional<Family> familyOpt = this.findById(id);
		if ((familyOpt.isPresent() && (familyOpt.get().getProductList().size() == 0))) {
			repo.deleteById(id);
		}
	}
	
	@Override
	public void delete(Family family) {
		this.deleteById(family.getId());
		//repo.delete(family);	
	}

	@Override
	public void deleteAll() {
		for(Family family : this.findAll()) {
			this.delete(family);
		}
		//repo.deleteAll();
	}

	@Override
	public Boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public Optional<Family> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public void truncate() {
		repo.truncate();
	}

	@Override
	public List<Family> findByName(String name) {
		return repo.findByName(name);
	}

}
