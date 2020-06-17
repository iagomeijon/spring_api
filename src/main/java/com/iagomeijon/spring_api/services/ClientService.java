package com.iagomeijon.spring_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iagomeijon.spring_api.domain.Client;
import com.iagomeijon.spring_api.repositories.ClientRepository;
import com.iagomeijon.spring_api.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	public Client findById(Integer id) {
		Optional<Client> op = repo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Object not founded"));
	}
}
