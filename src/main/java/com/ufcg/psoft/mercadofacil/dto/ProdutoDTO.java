package com.ufcg.psoft.mercadofacil.dto;

public class ProdutoDTO {

	private Long id;
	
	private String nome;

	private double preco;

	private String codigoBarra;

	private String fabricante;
	
	public ProdutoDTO() {}
			
	public ProdutoDTO(Long id, String nome, double preco, String codigoBarra, String fabricante) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.fabricante = fabricante;
		this.codigoBarra = codigoBarra;
	}

	public Long getId() {
		return id;
	}

	public void getId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
}
