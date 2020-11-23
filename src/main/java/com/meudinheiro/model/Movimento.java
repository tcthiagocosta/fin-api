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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_lancamento")
	@NotNull
	private Lancamento lancamento;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	@NotNull
	private Conta conta;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private LocalDate data;
	
	@Column
	@NotNull
	private BigDecimal valor;


}
