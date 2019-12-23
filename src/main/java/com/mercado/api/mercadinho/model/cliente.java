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
@Table(name = "TB_CLIENTE")
public class cliente implements Serializable {
	private static final long serialVersionUID = 4303639757977994514L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_CLIENTE")
	private Integer id;
	@Column(name = "NOME")
	private String nome;
	@Column(name = "NUMERO_DOCUMENTO")
	private String numeroDocumento;
	@Column(name = "NOME_PRODUTO")
	private String nomeProduto;
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
	@Column(name = "PRECO_UNIT")
	private Double precoUnit;
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = true)
	private registrosCompra registro;
	
	public cliente(){
	}
	
	public cliente(String nome, String numeroDocumento, Integer id,String nomeProduto,Integer quantidade,Double precoUnit) {
		super();
		this.id = id;
		this.nome = nome;
		this.numeroDocumento = numeroDocumento;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precoUnit = precoUnit;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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
	

	public Double getPrecoUnit() {
		return precoUnit;
	}

	public void setPrecoUnit(Double precoUnit) {
		this.precoUnit = precoUnit;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setRegistro(registrosCompra registro) {
		this.registro = registro;
	}
	
	
	
}
