package com.example.baithuchanh4.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.baithuchanh4.response.ProductsResponse;

@Service
public class RequestOtherPortService {

	@Autowired
	private RestTemplate restTemplate;

	public HttpHeaders header(String token) {
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", token);

		return header;
	}

	public Boolean auth(String token) {
		String url = "http://localhost:8080/auth/baithuchanh";

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class).getBody();
	}

	public void checkAuthentication(String token) {
		if (!auth(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
		}
	}

	public Optional<ProductsResponse> getProductById(int id, String token) {
		String url = "http://localhost:8081/products/" + id;

		HttpEntity<String> entity = new HttpEntity<>(header(token));
		
		return Optional.ofNullable(
				restTemplate.exchange(url, HttpMethod.GET, entity, ProductsResponse.class).getBody());
	}

	public Boolean minusOrAddQuantity(int id, String token, Object product) {
		String url = "http://localhost:8081/products/" + id;

		HttpEntity<Object> entity = new HttpEntity<>(product, header(token));

		return restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class).getBody();
	}
	
	public Optional<ProductsResponse> getProduct(int id, String token) {
		String url = "http://localhost:8081/products/" + id;

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return Optional.ofNullable(restTemplate.exchange(url, HttpMethod.GET, entity, ProductsResponse.class).getBody());
	}
}
