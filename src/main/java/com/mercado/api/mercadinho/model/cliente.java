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
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, optional = true)
	private registrosCompra registro;
	
	public cliente(){
	}
	
	public cliente(String nome, String numeroDocumento, Integer id) {
		super();
		this.id = id;
		this.nome = nome;
		this.numeroDocumento = numeroDocumento;
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

	public void setRegistro(registrosCompra registro) {
		this.registro = registro;
	}
	
}
