package org.core.curso.testspring.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.core.curso.testspring.data.model.Role;
import org.core.curso.testspring.data.repository.IRoleRepository;
import org.core.curso.testspring.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleRepository repo;

	@Override
	public List<Role> findAll() {
		return repo.findAll();
	}

	@Override
	public void showRole(Optional<Role> roleOpt) {
		System.out.println("\t" + (roleOpt.isPresent() ? roleOpt.get() : "No existe el id dado"));
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
		repo.save(new Role("USER"));
		repo.save(new Role("ADMIN"));
		repo.save(new Role("MANAGER"));		
		repo.save(new Role("CUSTOMER"));		
	}

	@Override
	public void save(Role role) {
		repo.save(role);
	}

	@Override
	public void deleteById(String rolename) {
		repo.deleteById(rolename);
	}
	
	@Override
	public void delete(Role role) {
		repo.delete(role);	
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public Boolean existsById(String rolename) {
		return repo.existsById(rolename);
	}

	@Override
	public Optional<Role> findById(String rolename) {
		return repo.findById(rolename);
	}

}
