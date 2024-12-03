package com.example.baithuchanh4.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RequestOtherPortService {

	@Autowired
	private RestTemplate restTemplate;

	public Boolean auth(String token) {
		String url = "http://localhost:8080/auth/baithuchanh";

		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", token);

		HttpEntity<String> entity = new HttpEntity<>(header);

		return restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class).getBody();
	}

	public void checkAuthentication(String token) {
		if (!auth(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
		}
	}

}
