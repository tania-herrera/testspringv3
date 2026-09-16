package org.core.curso.testspring.service;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface IRoleService {

	public void deleteAll();
	public void deleteById(Long id);
	public void delete(Role role);

	public String save(Role role);
	public void saveDataTest();
	
	public Boolean existsById(Long id);
	public Boolean existsByRolename(String rolename);
	public Long count();
	
	public Optional<Role> findById(Long id);
	public Optional<Role> findByRolename(String rolename);
	public List<Role> findAll();
	
	public void showAll();	
	public void showRole(Optional<Role> roleOpt);
	
	
}
