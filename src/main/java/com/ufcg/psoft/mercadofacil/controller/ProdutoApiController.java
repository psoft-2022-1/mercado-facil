package com.ufcg.psoft.mercadofacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ufcg.psoft.mercadofacil.dto.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.exception.ProdutoAlreadyCreatedException;
import com.ufcg.psoft.mercadofacil.exception.ProdutoNotFoundException;
import com.ufcg.psoft.mercadofacil.service.ProdutoService;
import com.ufcg.psoft.mercadofacil.util.ErroProduto;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProdutoApiController {

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping(value = "/produtos")
	public ResponseEntity<?> listarProdutos() {
		
		List<ProdutoDTO> produtos = produtoService.listarProdutos();
		if (produtos.isEmpty()) {
			return ErroProduto.erroSemProdutosCadastrados();
		}
		
		return new ResponseEntity<List<ProdutoDTO>>(produtos, HttpStatus.OK);
	}
	
	@PostMapping(value = "/produto/")
	public ResponseEntity<?> criarProduto(@RequestBody ProdutoDTO produtoDTO, UriComponentsBuilder ucBuilder) {

		try {
			ProdutoDTO produto = produtoService.criaProduto(produtoDTO);
			return new ResponseEntity<ProdutoDTO>(produto, HttpStatus.CREATED);
		} catch (ProdutoAlreadyCreatedException e) {
			return ErroProduto.erroProdutoJaCadastrado(produtoDTO);
		}
	}

	@GetMapping(value = "/produto/{id}")
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) {

		try {
			ProdutoDTO produto = produtoService.getProdutoById(id);
			return new ResponseEntity<ProdutoDTO>(produto, HttpStatus.OK);
		} catch (ProdutoNotFoundException e) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
	}
	
	@PutMapping(value = "/produto/{id}")
	public ResponseEntity<?> atualizarProduto(@PathVariable("id") long id, @RequestBody ProdutoDTO produtoDTO) {

		try {
			ProdutoDTO produto = produtoService.atualizaProduto(id, produtoDTO);
			return new ResponseEntity<ProdutoDTO>(produto, HttpStatus.OK);
		} catch (ProdutoNotFoundException e) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
	}

	@DeleteMapping(value = "/produto/{id}")
	public ResponseEntity<?> removerProduto(@PathVariable("id") long id) {

		try {
			produtoService.removerProdutoCadastrado(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ProdutoNotFoundException e) {
			return ErroProduto.erroProdutoNaoEnconrtrado(id);
		}
	}
}