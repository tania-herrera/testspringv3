package org.core.curso.testspring.service;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends UserDetailsService {

	public void deleteAll();
	public void deleteById(Long id);
	public void deleteByUsername(String username);
	public void delete(User user);

	public void save(User user);
	public void saveDataTestDev();
	public void saveDataTestProd();
	
	public Boolean existsById(Long id);
	public Boolean existsByUsername(String username);
	public Long count();
	
	public Optional<User> findById(Long id);
	public Optional<User> findByUsername(String username);
	public Optional<User> findByEmail(String email);
	public List<User> findAll();
	
	public void showAll();	
	public void showUser(Optional<User> userOpt);
	
}
