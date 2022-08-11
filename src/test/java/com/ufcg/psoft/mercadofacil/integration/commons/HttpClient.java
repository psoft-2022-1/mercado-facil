package com.ufcg.psoft.mercadofacil.integration.commons;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class HttpClient {

    private final String SERVER_URL = "http://localhost";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String getBaseEndpoint() {
        return SERVER_URL + ":" + port;
    }
    
    public ResponseResults execute(String uri, HttpMethod method) throws IOException {
    	String url = getBaseEndpoint() + uri;

        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        return executeResquest(url, headers, method);
    }

    public List<?> executeGetContents(String uri) {
    	String url = getBaseEndpoint() + uri;
    	return restTemplate.getForObject(url, List.class);
    }
    
	private ResponseResults executeResquest(String url, final Map<String, String> headers, HttpMethod method) {
		final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        restTemplate.setErrorHandler(errorHandler);
        return restTemplate.execute(url, method, requestCallback, response -> {
              if (errorHandler.hadError) {
                  return (errorHandler.getResults());
              } else {
                  return (new ResponseResults(response));
              }
          });
	}
    
    public void clean(String uri) {
    	String url = getBaseEndpoint() + uri;
        restTemplate.delete(url);
    }

    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
}
