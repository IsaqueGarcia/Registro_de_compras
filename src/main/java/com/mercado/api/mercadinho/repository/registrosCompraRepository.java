package com.mercado.api.mercadinho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercado.api.mercadinho.model.cliente;
import com.mercado.api.mercadinho.model.produto;
import com.mercado.api.mercadinho.model.registrosCompra;

@Repository
public interface registrosCompraRepository extends JpaRepository<registrosCompra, Integer> {

	@Query("select pr from produto pr where pr.id = :id")
	public produto buscaProdutoPeloId(@Param("id") Integer id);
	
	@Query("select cli from cliente cli where cli.id = :id")
	public cliente buscaClientePeloId(@Param("id") Integer id);
	
	@Query("select r.id, r.dtCompra,r.precoTotal,r.precoUnit,r.nomeCliente,r.nomeProduto,r.qtd from registrosCompra r where r.id  = :id")
	public String buscaRegistro(@Param("id") Integer id);
	
	@Query("select sum(precoTotal) from registrosCompra")
	public Double valorTotal();
	
	@Query("select r from registrosCompra r")
	public List<registrosCompra> buscaTodos();
	
}
