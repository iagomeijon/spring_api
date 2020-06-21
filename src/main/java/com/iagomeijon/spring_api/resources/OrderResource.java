package com.iagomeijon.spring_api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iagomeijon.spring_api.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
