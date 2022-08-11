package com.ufcg.psoft.mercadofacil.integration.basics.steps;

import io.cucumber.java.ParameterType;

public class BaseStepDefinitions {

    @ParameterType(".*")
    public Long prodId(String value) {
    	return Long.valueOf(value);
    }
    
    @ParameterType(".*")
    public String uriProds(String value) {
    	return value;
    }
    
    @ParameterType(".*")
    public String elements(String value) {
    	return value;
    }
    
    @ParameterType(".*")
    public String uri(String value) {
    	return value;
    }
}
