package com.ufcg.psoft.mercadofacil.dto;

import com.ufcg.psoft.mercadofacil.model.Produto;

public class LoteDTO {

    private Long id;
    private Produto produto;
    private int numeroDeItens;

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }
}
