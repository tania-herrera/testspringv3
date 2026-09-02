package org.core.curso.testspring.service;

import java.util.List;
import java.util.Optional;

import org.core.curso.testspring.data.model.Family;
import org.springframework.stereotype.Service;

@Service
public interface IFamilyService {

	public void deleteAll();
	public void deleteById(Long id);
	public void delete(Family family);
	public void truncate();

	public void save(Family family);
	public void saveDataTest();
	
	public Boolean existsById(Long id);
	public Long count();
	
	public Optional<Family> findById(Long id);
	public List<Family> findAll();
	public List<Family> findByName(String name);

	public void showAll();	
	public void showItem(Optional<Family> itemOpt);
	
}
