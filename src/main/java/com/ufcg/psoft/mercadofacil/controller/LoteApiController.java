package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.dto.LoteDTO;
import com.ufcg.psoft.mercadofacil.exception.ProdutoNotFoundException;
import com.ufcg.psoft.mercadofacil.service.LoteService;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroLote;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoteApiController {

	@Autowired
	LoteService loteService;
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping(value = "/lotes")
	public ResponseEntity<?> listarLotes() {
		
		List<LoteDTO> lotes = loteService.listarLotes();

		if (lotes.isEmpty()) {
			return ErroLote.erroSemLotesCadastrados();
		}
		
		return new ResponseEntity<List<LoteDTO>>(lotes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/produto/{idProduto}/lote/")
	public ResponseEntity<?> criarLote(@PathVariable("idProduto") long id, @RequestBody int numItens) {
			
		try {
			LoteDTO lote = loteService.criaLote(numItens, id);
			return new ResponseEntity<LoteDTO>(lote, HttpStatus.CREATED);
		} catch (ProdutoNotFoundException e) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
	}
}