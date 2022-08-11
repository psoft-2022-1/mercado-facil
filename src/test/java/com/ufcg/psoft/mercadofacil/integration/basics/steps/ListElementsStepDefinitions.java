package com.ufcg.psoft.mercadofacil.integration.basics.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.ufcg.psoft.mercadofacil.dto.ProdutoDTO;
import com.ufcg.psoft.mercadofacil.integration.commons.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ListElementsStepDefinitions {

    @Autowired
    private HttpClient httpClient;

    private List<?> contents; 
    
    private final Logger log = LoggerFactory.getLogger(ListElementsStepDefinitions.class);
    
    @When("the client list {elements} by calling {uriProds}")
    public void the_client_issues_outra(String elements, String uri) throws Throwable {
    	this.contents = httpClient.executeGetContents(uri);
    }
    
    @And("the client retrives a list of {int} elements")
    public void assertNumberOfProducts(final int quantity) {
    	final int size = contents.size();
    	assertThat(size).isEqualTo(quantity);
    }
    
    @Then("check if the ID of the first product is {prodId}") 
    public void assertProductId(final Long idProd) throws Throwable {
    	String prodJson = new ObjectMapper().writeValueAsString(contents.get(0));
    	ProdutoDTO produto = new Gson().fromJson(prodJson, ProdutoDTO.class);
    	final Long id = produto.getId();
    	assertThat(id).isEqualTo(idProd);
    }
}
