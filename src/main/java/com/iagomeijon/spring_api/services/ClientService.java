package com.iagomeijon.spring_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.iagomeijon.spring_api.domain.Client;
import com.iagomeijon.spring_api.dto.ClientDTO;
import com.iagomeijon.spring_api.repositories.ClientRepository;
import com.iagomeijon.spring_api.services.exceptions.DataIntegratyException;
import com.iagomeijon.spring_api.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	public Client findById(Integer id) {
		Optional<Client> op = repo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Object not founded"));
	}
	
	public Client update(Client clientUpdated) {
		Client client = findById(clientUpdated.getId());
		updateData(client, clientUpdated);
		return repo.save(client);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyException("Data Integrity violaion");
		}
		
	}

	public List<Client> getAll() {
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
		orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO (ClientDTO obj) {
		return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null);
	}
	
	private void updateData(Client newClient, Client clientUpdated) {
		newClient.setName(clientUpdated.getName());
		newClient.setEmail(clientUpdated.getEmail());
		
	}

}
