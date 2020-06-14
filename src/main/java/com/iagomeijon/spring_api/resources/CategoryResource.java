package com.iagomeijon.spring_api.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@GetMapping
	public String restTest() {
		return "thats ok!";
	}
}
