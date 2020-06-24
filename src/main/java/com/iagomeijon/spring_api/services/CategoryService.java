package com.iagomeijon.spring_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iagomeijon.spring_api.domain.Category;
import com.iagomeijon.spring_api.repositories.CategoryRepository;
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
}
