package com.iagomeijon.spring_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iagomeijon.spring_api.domain.Order;
import com.iagomeijon.spring_api.repositories.OrderRepository;
import com.iagomeijon.spring_api.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

		@Autowired
		private OrderRepository repo;
		
		public Order findById(Integer id) {
			Optional<Order> op = repo.findById(id);
			return op.orElseThrow(() -> new ObjectNotFoundException("Object not founded"));
		}
		
}
