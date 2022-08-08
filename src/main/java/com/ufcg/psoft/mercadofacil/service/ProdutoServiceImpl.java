package com.ufcg.psoft.mercadofacil.service;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.dto.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.exception.ProdutoAlreadyCreatedException;
import com.ufcg.psoft.mercadofacil.exception.ProdutoNotFoundException;
import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	public ProdutoDTO getProdutoById(Long id) throws ProdutoNotFoundException {
		Produto produto = getProduto(id);
		return modelMapper.map(produto, ProdutoDTO.class);
	}
	
	public Produto getProduto(Long id) throws ProdutoNotFoundException {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ProdutoNotFoundException());
		return produto;
	}
	
	public ProdutoDTO getProdutoByCodigoBarra(String codigo) throws ProdutoNotFoundException {
		Produto produto = produtoRepository.findByCodigoBarra(codigo)
				.orElseThrow(() -> new ProdutoNotFoundException());
		return modelMapper.map(produto, ProdutoDTO.class);
	}
	
	public void removerProdutoCadastrado(Long id) throws ProdutoNotFoundException {
		Produto produto = getProduto(id);
		produtoRepository.delete(produto);
	}

	private void salvarProdutoCadastrado(Produto produto) {
		produtoRepository.save(produto);		
	}

	public List<ProdutoDTO> listarProdutos() {
		List<ProdutoDTO> produtos = produtoRepository.findAll()
				.stream()
				.map(produto -> modelMapper.map(produto, ProdutoDTO.class))
				.collect(Collectors.toList());

		return produtos;
	}

	public ProdutoDTO criaProduto(ProdutoDTO produtoDTO) throws ProdutoAlreadyCreatedException {
		
		if(isProdutoCadastrado(produtoDTO.getCodigoBarra())) {
			throw new ProdutoAlreadyCreatedException();
		}
			
		Produto produto = new Produto(produtoDTO.getNome(), produtoDTO.getFabricante(), produtoDTO.getCodigoBarra(),
				produtoDTO.getPreco());
		salvarProdutoCadastrado(produto);

		return modelMapper.map(produto, ProdutoDTO.class);
	}

	public ProdutoDTO atualizaProduto(Long id, ProdutoDTO produtoDTO) throws ProdutoNotFoundException {
	
		Produto produto = getProduto(id);
		
		produto.setNome(produtoDTO.getNome());
		produto.setPreco(produtoDTO.getPreco());
		produto.setCodigoBarra(produtoDTO.getCodigoBarra());
		produto.setFabricante(produtoDTO.getFabricante());
		salvarProdutoCadastrado(produto);

		return modelMapper.map(produto, ProdutoDTO.class);
	}
	
	private boolean isProdutoCadastrado(String codigoBarra) {
		try {
			getProdutoByCodigoBarra(codigoBarra);
			return true;
		} catch (ProdutoNotFoundException e) {
			return false;
		}
	}
}
