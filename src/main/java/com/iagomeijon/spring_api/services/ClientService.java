package com.iagomeijon.spring_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iagomeijon.spring_api.domain.Address;
import com.iagomeijon.spring_api.domain.City;
import com.iagomeijon.spring_api.domain.Client;
import com.iagomeijon.spring_api.domain.enums.ClientType;
import com.iagomeijon.spring_api.dto.ClientDTO;
import com.iagomeijon.spring_api.dto.ClientNewDTO;
import com.iagomeijon.spring_api.repositories.AddressRepository;
import com.iagomeijon.spring_api.repositories.ClientRepository;
import com.iagomeijon.spring_api.services.exceptions.DataIntegratyException;
import com.iagomeijon.spring_api.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	public Client findById(Integer id) {
		Optional<Client> op = repo.findById(id);
		return op.orElseThrow(() -> new ObjectNotFoundException("Object not founded"));
	}
	
	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		addressRepo.saveAll(client.getAddressses());
		repo.save(client);
		return null;
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
	
	public Client fromDTO (ClientNewDTO obj) {
		Client client = new Client(null, obj.getName(), obj.getEmail(), obj.getDocument(), ClientType.toEnum(obj.getType()));
		City city = new City(obj.getCityId(), null, null);
		Address adr = new Address(null, obj.getStreet(), obj.getNeighborhood(), obj.getNumber()
				, obj.getCodePost(), client, city);
		client.getAddressses().add(adr);
		client.getPhones().add(obj.getPhone1());
		if (obj.getPhone2() !=null) {
			client.getPhones().add(obj.getPhone2());
		}
		if (obj.getPhone3() !=null) {
			client.getPhones().add(obj.getPhone3());
		}
		return client;
	}
	
	private void updateData(Client newClient, Client clientUpdated) {
		newClient.setName(clientUpdated.getName());
		newClient.setEmail(clientUpdated.getEmail());
		
	}

}
