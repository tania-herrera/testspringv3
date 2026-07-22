package org.core.curso.testspring.data.repository;

import java.util.Optional;

import org.core.curso.testspring.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	public Boolean existsByUsername(String username);
	public Optional<User> findOptByUsername(String username);
	
	public Boolean existsByEmail(String email);
	public Optional<User> findByEmail(String email);
	
	public void deleteByUsername(String username);
	
}
