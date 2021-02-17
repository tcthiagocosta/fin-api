package com.meudinheiro.dto;

import java.math.BigDecimal;

import com.meudinheiro.model.Cartao;
import com.meudinheiro.model.Categoria;
import com.meudinheiro.model.Conta;
import com.meudinheiro.model.FaturaCartao;
import com.meudinheiro.model.LancamentoFatura;
import com.meudinheiro.model.SubCategoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestBodyLancamentoFatura {
	
	private LancamentoFatura lancamentoFatura;
	private Cartao cartao;

}
