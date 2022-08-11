package com.ufcg.psoft.mercadofacil.integration.basics.steps;

import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.ufcg.psoft.mercadofacil.integration.commons.HttpClient;
import com.ufcg.psoft.mercadofacil.integration.commons.ResponseResults;

import org.springframework.http.HttpMethod;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RequestStatusStepDefinitions {

    @Autowired
    private HttpClient httpClient;

    private ResponseResults response; 
        
    @When("the client calls {uri}")
    public void the_client_issues_GET_version(String uri) throws Throwable {
    	this.response = httpClient.execute(uri, HttpMethod.GET);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(final int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = this.response.getTheResponse().getStatusCode();
        assertThat(currentStatusCode.value()).isEqualTo(statusCode);
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(final String version) throws Throwable {
    	assertThat(this.response.getBody()).isEqualTo(version);
    }
}
