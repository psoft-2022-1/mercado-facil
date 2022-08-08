package com.ufcg.psoft.mercadofacil.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.dto.LoteDTO;
import com.ufcg.psoft.mercadofacil.exception.ProdutoNotFoundException;
import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.LoteRepository;

@Service
public class LoteServiceImpl implements LoteService {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	public List<LoteDTO> listarLotes() {
		
		List<LoteDTO> lotes = loteRepository.findAll()
				.stream()
				.map(lote -> modelMapper.map(lote, LoteDTO.class))
				.collect(Collectors.toList());

		return lotes;
	}

	private void salvarLote(Lote lote) {
		loteRepository.save(lote);		
	}

	public LoteDTO criaLote(int numItens, Long id) throws ProdutoNotFoundException {
		
		Produto produto = produtoService.getProduto(id);
		Lote lote = new Lote(produto, numItens);
		salvarLote(lote);
	
		return modelMapper.map(lote, LoteDTO.class);
	}

}
