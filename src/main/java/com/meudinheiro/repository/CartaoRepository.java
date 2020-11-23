package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
	
	List<Cartao> findAllByOrderByIdAsc();
}
