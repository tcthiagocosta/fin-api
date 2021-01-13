package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
	
	List<Transferencia> findAllByOrderByIdAsc();
}
