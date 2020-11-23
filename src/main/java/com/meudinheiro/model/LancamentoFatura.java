package com.meudinheiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LancamentoFatura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_fatura_cartao")
	private FaturaCartao faturaCartao;

	@ManyToOne
	@JoinColumn(name = "id_sub_categoria")
	private SubCategoria subCategoria;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	@Column(length = 2)
	private Integer parcela;

	@Column
	private BigDecimal valor;
	
	@Column(length = 200)
	private String descricao;
	
	@Column(length = 1)
	private char tipo;
	
	
}
