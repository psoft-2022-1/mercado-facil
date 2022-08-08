package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import com.ufcg.psoft.mercadofacil.dto.LoteDTO;
import com.ufcg.psoft.mercadofacil.exception.ProdutoNotFoundException;

public interface LoteService {
	
	public List<LoteDTO> listarLotes();

	public LoteDTO criaLote(int numItens, Long prodId) throws ProdutoNotFoundException;
	
}
