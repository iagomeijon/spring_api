package com.iagomeijon.spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iagomeijon.spring_api.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
}
