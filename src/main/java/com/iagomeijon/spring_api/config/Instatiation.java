package com.iagomeijon.spring_api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.iagomeijon.spring_api.domain.Category;
import com.iagomeijon.spring_api.repositories.CategoryRepository;

@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public void run(String... args) throws Exception {
		categoryRepository.saveAll(Arrays.asList(new Category(null, "Computing"), 
						new Category(null, "Home"), 
						new Category(null, "Office")));
	}

}
