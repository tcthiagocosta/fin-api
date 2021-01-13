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
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	@ManyToOne
	@JoinColumn(name = "id_conta_destino")
	private Conta contaDestino;
	
	@Column
	private BigDecimal valor;
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;	

}
