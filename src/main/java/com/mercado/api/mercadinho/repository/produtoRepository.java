package com.mercado.api.mercadinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercado.api.mercadinho.model.produto;

@Repository
public interface produtoRepository extends JpaRepository<produto, Integer> {
	
	@Query("select pr from produto pr where pr.nome = :nome")
	public produto buscaProdutoPeloNome(@Param("nome") String nome);
}
