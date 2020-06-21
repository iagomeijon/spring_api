package com.iagomeijon.spring_api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iagomeijon.spring_api.domain.enums.ClientType;

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String document;
	private Integer type;
	
	@JsonManagedReference
	@OneToMany(mappedBy="client")
	private List<Address> Addressses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="PHONE")
	private Set<String> phones = new HashSet<>();
	
	@JsonBackReference
	@OneToMany(mappedBy="client")
	private List<Order> order = new ArrayList<>();
	
	public Client() {
	}

	public Client(Integer id, String nome, String email, String cpfOuCnpj, ClientType type) {
		super();
		this.id = id;
		this.name = nome;
		this.email = email;
		this.document = cpfOuCnpj;
		this.type = type.getId();
	}


	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDocument() {
		return document;
	}
	
	public void setDocument(String document) {
		this.document = document;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public List<Address> getAddressses() {
		return Addressses;
	}
	
	public void setAddressses(List<Address> enderecos) {
		this.Addressses = enderecos;
	}
	
	public Set<String> getPhones() {
		return phones;
	}
	
	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}
	
	public List<Order> getOrder() {
		return order;
	}
	
	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}