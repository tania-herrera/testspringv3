package org.core.curso.testspring.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.core.curso.testspring.data.model.Role;
import org.core.curso.testspring.data.repository.IRoleRepository;
import org.core.curso.testspring.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Log
@Service
public class RoleService implements IRoleService {
	
	private final IRoleRepository repo;
	
	public RoleService(IRoleRepository repo) {
		super();
		this.repo = repo;
	}

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
	public String save(Role role) {
		role.setRolename(role.getRolename().toUpperCase());
		Role role2 = null;
		if (role.getId() == null) {
			if (!this.existsByRolename(role.getRolename())) {
				role2 = repo.save(role);
				return "Nueva alta Ok";
			}
			else {
				return "Alta KO por duplicidad en el rolename";
			}
		}
		else { 
			Optional<Role> roleSearched = this.findByRolename(role.getRolename());		
			if (roleSearched.isEmpty()) { 
				role2 = repo.save(role);
				return "Actualización Ok";
			}
			else {
				return "Actualización KO por duplicidad en el rolename";			
			}
		}
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
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
	public Boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public Boolean existsByRolename(String rolename) {
		return repo.existsByRolename(rolename);
	}
	
	@Override
	public Optional<Role> findById(Long id) {
		return repo.findById(id);
	}
	
	@Override
	public Optional<Role> findByRolename(String rolename) {
		return repo.findByRolename(rolename);
	}

}
