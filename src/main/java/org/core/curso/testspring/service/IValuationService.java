package org.core.curso.testspring.service;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Valuation;
import org.springframework.stereotype.Service;

@Service
public interface IValuationService {

	public void deleteAll();
	public void deleteById(Long id);
	public void delete(Valuation valuation);
	public void truncate();

	public void save(Valuation valuation);
	public void saveDataTest();
	
	public Boolean existsById(Long id);
	public Long count();
	
	public Optional<Valuation> findById(Long id);
	public List<Valuation> findAll();
	public List<Valuation> findByName(String name);

	
	public void showAll();	
	public void showItem(Optional<Valuation> itemOpt);
	
}
