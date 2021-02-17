package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meudinheiro.model.Cartao;
import com.meudinheiro.model.FaturaCartao;

public interface FaturaCartaoRepository extends JpaRepository<FaturaCartao, Integer> {
	
	List<FaturaCartao> findAllByOrderByIdAsc();
	
	FaturaCartao findByMesAndAnoAndCartao(Integer mes, Integer ano, Cartao cartao);

}
