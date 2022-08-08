package com.ufcg.psoft.mercadofacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.mercadofacil.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Optional<Produto> findByCodigoBarra(String codigoBarra);
}
