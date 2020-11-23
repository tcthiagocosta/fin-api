package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
	
	 List<Conta> findAllByOrderByIdAsc();

}
