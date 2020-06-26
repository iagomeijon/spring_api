package com.iagomeijon.spring_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.iagomeijon.spring_api.domain.Category;
import com.iagomeijon.spring_api.dto.CategoryDTO;
import com.iagomeijon.spring_api.repositories.CategoryRepository;
import com.iagomeijon.spring_api.services.exceptions.DataIntegratyException;
import com.iagomeijon.spring_api.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category findById(Integer id) {
		Optional<Category> op = repo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Object not founded"));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return repo.save(category);
	}

	public Category update(Category category) {
		findById(category.getId());
		return repo.save(category);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyException("Data Integrity violaion");
		}
		
	}

	public List<Category> getAll() {
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
		orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO (CategoryDTO obj) {
		return new Category(obj.getId(), obj.getName());
	}
}
