package com.ufcg.psoft.mercadofacil.unit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ufcg.psoft.mercadofacil.MercadoFacilApplication;
import com.ufcg.psoft.mercadofacil.dto.ProdutoDTO;


import java.net.URI;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MercadoFacilApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTests {

	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateProduto() throws Exception {
		
		final String baseUrl = "http://localhost:" + serverPort + "/api";
		
		// Get last product id
		URI uri = new URI(baseUrl + "/produtos");        
		List<ProdutoDTO> responseProduto = this.restTemplate.getForObject(uri, List.class);
		
		String lastProdJson = new ObjectMapper().writeValueAsString(responseProduto.get(responseProduto.size()-1));
		ProdutoDTO lastProduto = new Gson().fromJson(lastProdJson, ProdutoDTO.class);
		int lastProdId = lastProduto.getId().intValue();
		
		// Create a new product with a empty id
		uri = new URI(baseUrl + "/produto/");
		ProdutoDTO produtoDTO = new ProdutoDTO(0L, "Leite", 7.5, "110001001BR", "Parmalat");
		
		HttpEntity<ProdutoDTO> request = new HttpEntity<>(produtoDTO);
        ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);
       
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).contains((lastProdId + 1) + "");
        
        // Check the last product id
		uri = new URI(baseUrl + "/produtos");        
		responseProduto = this.restTemplate.getForObject(uri, List.class);
        
		assertThat(responseProduto).isNotEmpty();
		
    	String prodJson = new ObjectMapper().writeValueAsString(responseProduto.get(responseProduto.size()-1));
    	ProdutoDTO produto = new Gson().fromJson(prodJson, ProdutoDTO.class);

    	assertThat(produto.getId().intValue()).isEqualTo(lastProdId + 1);
	}
}


