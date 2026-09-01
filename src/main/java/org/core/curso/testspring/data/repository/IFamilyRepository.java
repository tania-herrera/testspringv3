package org.core.curso.testspring.data.repository;

import java.util.List;

import org.core.curso.testspring.data.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface IFamilyRepository extends JpaRepository<Family, Long> {

	public List<Family> findByName(String name);
	
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE FAMILY", nativeQuery = true)
	public void truncate();
	
}
