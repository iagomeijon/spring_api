package com.iagomeijon.spring_api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iagomeijon.spring_api.domain.Client;
import com.iagomeijon.spring_api.dto.ClientDTO;
import com.iagomeijon.spring_api.dto.ClientNewDTO;
import com.iagomeijon.spring_api.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	private ClientService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ClientNewDTO clientDTO) {
		Client client = service.fromDTO(clientDTO);
		client = service.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody ClientDTO clientDTO, @PathVariable Integer id) {
			clientDTO.setId(id);
			Client client  = service.fromDTO(clientDTO);
			client = service.update(client);
			return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> clients = service.getAll();
		List<ClientDTO> clientsDTO = clients.stream()
				.map( obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(clientsDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "2") Integer linesPerPage, 
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) 
	{
		Page<Client> categories = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> categoryDTO = categories.map( obj -> new ClientDTO(obj));
		return ResponseEntity.ok(categoryDTO);
	}
}
