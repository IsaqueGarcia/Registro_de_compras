package com.mercado.api.mercadinho.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO")
public class produto implements Serializable {
	private static final long serialVersionUID = -888617319428334676L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_PRODUTO")
	private Integer id;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "QTD")
	private Integer quantidade;
	@Column(name = "PRECO_PRODUTO")
	private Double preco;
	@Column(name = "TIPO_DO_PRODUTO")
	private String tipo;
	@Column(name = "FORNECEDOR")
	private String fornecedor;
	
	@OneToOne(mappedBy = "produto", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = true)
	private registrosCompra registro;
	
	public produto(){
	}

	public produto(String nome, Integer quantidade, String tipo, String fornecedor,Integer id,Double preco) {
		super();
		this.id = id;
		this.preco = preco;
		this.nome = nome;
		this.quantidade = quantidade;
		this.tipo = tipo;
		this.fornecedor = fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public registrosCompra getRegistro() {
		return registro;
	}

	public void setRegistro(registrosCompra registro) {
		this.registro = registro;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
