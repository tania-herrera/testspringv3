package org.core.curso.testspring.data.repository;

import java.util.List;

import org.core.curso.testspring.data.model.Valuation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface IValuationRepository extends JpaRepository<Valuation, Long> {

	public List<Valuation> findByName(String name);
	
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE VALUATION", nativeQuery = true)
	public void truncate();
	
}
