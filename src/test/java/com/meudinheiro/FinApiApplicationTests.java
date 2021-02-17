package com.meudinheiro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meudinheiro.model.Cartao;
import com.meudinheiro.model.FaturaCartao;
import com.meudinheiro.service.FaturaCartaoService;

@SpringBootTest
class FinApiApplicationTests {
	
	@Autowired
	private FaturaCartaoService faturaCartaoService; 

	@Test
	void contextLoads() {
		Cartao cartao = new Cartao();
		cartao.setId(1);
		
		FaturaCartao faturaCartao = faturaCartaoService.getFaturaPorMesEAnoECartao(1, 2021, cartao);
		
		assertThat(faturaCartao).isNotNull();
	}

}
