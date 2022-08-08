package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import com.ufcg.psoft.mercadofacil.dto.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.exception.ProdutoAlreadyCreatedException;
import com.ufcg.psoft.mercadofacil.exception.ProdutoNotFoundException;
import com.ufcg.psoft.mercadofacil.model.Produto;

public interface ProdutoService {

	public ProdutoDTO getProdutoById(Long id) throws ProdutoNotFoundException;
	
	public ProdutoDTO getProdutoByCodigoBarra(String codigoBarra) throws ProdutoNotFoundException;
	
	public List<ProdutoDTO> listarProdutos();
	
	public void removerProdutoCadastrado(Long id) throws ProdutoNotFoundException;
	
	public ProdutoDTO criaProduto(ProdutoDTO produtoDTO) throws ProdutoAlreadyCreatedException;
	
	public ProdutoDTO atualizaProduto(Long id, ProdutoDTO produtoDTO) throws ProdutoNotFoundException;

	public Produto getProduto(Long id) throws ProdutoNotFoundException;
}
