package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.LancamentoFatura;

public interface LancamentoFaturaRepository extends JpaRepository<LancamentoFatura, Integer> {

	List<LancamentoFatura> findAllByOrderByIdAsc();
}
