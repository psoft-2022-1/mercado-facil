package com.ufcg.psoft.mercadofacil.integration.commons;

import org.apache.commons.io.IOUtils;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ResponseResults {
	
    private final ClientHttpResponse theResponse;
    private final List<?> listResponse;
    private final String body;

    ResponseResults(final ClientHttpResponse response) throws IOException {
        this.theResponse = response;
        final InputStream bodyInputStream = response.getBody();
        final StringWriter stringWriter = new StringWriter();
        IOUtils.copy(bodyInputStream, stringWriter);
        this.body = stringWriter.toString();
        this.listResponse = null;
    }
    
    ResponseResults(final List<?> response) {
    	this.theResponse = null;
		this.listResponse = response;
		this.body = "";
    }
    
    public List<?> getListResponse() {
    	return listResponse;
    }

    public ClientHttpResponse getTheResponse() {
        return theResponse;
    }

    public String getBody() {
        return body;
    }
}