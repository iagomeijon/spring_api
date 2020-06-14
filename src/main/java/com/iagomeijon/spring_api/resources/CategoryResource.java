package com.iagomeijon.spring_api.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iagomeijon.spring_api.domain.Category;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@GetMapping
	public List<Category>restTest() {
		Category c1 = new Category(1,"Computing");
		Category c2 = new Category(2,"Office");
		return new ArrayList<Category>(Arrays.asList(c1,c2));
	}
}
