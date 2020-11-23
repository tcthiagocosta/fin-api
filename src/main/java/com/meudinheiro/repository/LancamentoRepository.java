package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
	
	List<Lancamento> findAllByOrderByIdAsc();
}
