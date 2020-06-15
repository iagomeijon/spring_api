package com.iagomeijon.spring_api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.iagomeijon.spring_api.domain.Category;
import com.iagomeijon.spring_api.domain.City;
import com.iagomeijon.spring_api.domain.Product;
import com.iagomeijon.spring_api.domain.State;
import com.iagomeijon.spring_api.repositories.CategoryRepository;
import com.iagomeijon.spring_api.repositories.CityRepository;
import com.iagomeijon.spring_api.repositories.ProductRepository;
import com.iagomeijon.spring_api.repositories.StateRepository;

@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//MARK: Product and category
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
		
		//MARK: State and city
		State st1 = new State(null, "Bahia");
		State st2 = new State(null, "São paulo");
		
		City city1 = new City(null, "Salvador", st1);
		City city2 = new City(null, "São paulo", st2);
		City city3 = new City(null, "Santos", st2);
		
		st1.getCities().add(city1);
		st2.getCities().addAll(Arrays.asList(city2, city3));
		
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
	}

}
