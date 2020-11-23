package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.SubCategoria;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer> {

	List<SubCategoria> findAllByOrderByIdAsc();
}