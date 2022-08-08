package com.ufcg.psoft.mercadofacil.dto;

public class ClienteDTO {

	private Long id; 
	
	private Long cpf;
	
	private String nome;

	private Integer idade;

	private String endereco;

	public Long getId() {
		return id;
	}
	
	public Long getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}
	
	public Integer getIdade() {
		return idade;
	}

	public String getEndereco() {
		return endereco;
	}
}
