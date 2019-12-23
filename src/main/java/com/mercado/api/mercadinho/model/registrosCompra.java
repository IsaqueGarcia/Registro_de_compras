package com.mercado.api.mercadinho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "REGISTRO_DE_COMPRA")
@Getter
@Setter
public class registrosCompra implements Serializable {
	private static final long serialVersionUID = -8870163296155407361L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOME_CLIENTE")
	private String nomeCliente;

	@Column(name = "DATA_COMPRA")
	private String dtCompra;

	@Column(name = "QUANTIDADE")
	private Integer qtd;

	@Column(name = "PRODUTO_COMPRADO")
	private String nomeProduto;

	@Column(name = "PRECO_UNITARIO")
	private Double precoUnit;

	@Column(name = "PRECO_TOTAL")
	private Double precoTotal;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PK_CLIENTE")
	private cliente cliente;

	@Transient
	private Integer idCliente;

	public registrosCompra() {
		super();
	}

	public registrosCompra(Integer id, String dtCompra,
			com.mercado.api.mercadinho.model.cliente cliente, Double precoTotal, Double precoUnit, String nomeCliente,
			String nomeProduto, Integer qtd) {
		super();
		this.nomeCliente = nomeCliente;
		this.nomeProduto = nomeProduto;
		this.precoUnit = precoUnit;
		this.precoTotal = precoTotal;
		this.id = id;
		this.qtd = qtd;
		this.dtCompra = dtCompra;
		this.cliente = cliente;
	}

}
