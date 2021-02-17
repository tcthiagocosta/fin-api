package com.meudinheiro.dto;

import java.math.BigDecimal;

import com.meudinheiro.model.Categoria;
import com.meudinheiro.model.Conta;
import com.meudinheiro.model.FaturaCartao;
import com.meudinheiro.model.SubCategoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LancamentoDTO {
	
	private Integer id;
	private Conta conta;
	private Categoria categoria;
	private FaturaCartao faturaCartao;
	private SubCategoria subCategoria;
	private String descricao;
	private String tipo;
	private BigDecimal valor;
	private String data;
	private boolean pago;
	private String dataPagamento;

}
