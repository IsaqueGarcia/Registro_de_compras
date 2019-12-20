package com.mercado.api.mercadinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercado.api.mercadinho.model.cliente;

@Repository
public interface clienteRepository extends JpaRepository<cliente, Integer> {

	@Query("select cli from cliente cli where cli.id = :id")
	public cliente buscaCliente(@Param("id") Integer id);
}
