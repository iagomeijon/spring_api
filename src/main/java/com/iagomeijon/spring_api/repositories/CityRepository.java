package com.iagomeijon.spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iagomeijon.spring_api.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
}
