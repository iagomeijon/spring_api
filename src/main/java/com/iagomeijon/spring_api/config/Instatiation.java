package com.iagomeijon.spring_api.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.iagomeijon.spring_api.domain.Category;
import com.iagomeijon.spring_api.domain.Product;
import com.iagomeijon.spring_api.repositories.CategoryRepository;
import com.iagomeijon.spring_api.repositories.ProductRepository;

@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	@Override
	public void run(String... args) throws Exception {
		
		Product p1 = new Product(null, "Computer", 3000.00);
		Product p2 = new Product(null, "Priter", 250.99);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		Category c1 = new Category(null, "Computing");
		Category c3 = new Category(null, "Home");
		Category c2 = new Category(null, "Office");
		
		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().add(p2);
		
		p1.getCategories().add(c1);
		p2.getCategories().addAll(Arrays.asList(c1, c2));
		p3.getCategories().add(c1);
		
		categoryRepository.saveAll(Arrays.asList(c1, c2 ,c3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
