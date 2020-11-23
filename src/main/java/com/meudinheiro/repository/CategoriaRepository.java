package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	List<Categoria> findAllByOrderByIdAsc();

}
