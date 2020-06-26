package com.iagomeijon.spring_api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iagomeijon.spring_api.domain.Category;
import com.iagomeijon.spring_api.dto.CategoryDTO;
import com.iagomeijon.spring_api.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Category category) {
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Category category, @PathVariable Integer id) {
			category.setId(id);
			category = service.update(category);
			return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> categories = service.getAll();
		List<CategoryDTO> categoryDTO = categories.stream()
				.map( obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(categoryDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "2") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) 
	{
		Page<Category> categories = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> categoryDTO = categories.map( obj -> new CategoryDTO(obj));
		return ResponseEntity.ok(categoryDTO);
	}
	
}
