package com.iagomeijon.spring_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iagomeijon.spring_api.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
