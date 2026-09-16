package org.core.curso.testspring.data.repository;

import java.util.Optional;

import org.core.curso.testspring.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRolename(String rolename);

	Boolean existsByRolename(String rolename);



}
