package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.Movimento;

public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {

	List<Movimento> findAllByOrderByIdAsc();
}
