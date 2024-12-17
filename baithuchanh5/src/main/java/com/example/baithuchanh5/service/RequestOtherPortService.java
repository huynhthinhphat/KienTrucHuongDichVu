package com.example.baithuchanh5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.baithuchanh5.entities.ProductReports;
import com.example.baithuchanh5.request.ProductReportsRequest;

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

	public int[] getProduct(int product_id, int id, String token) {
		String url = "http://localhost:8082/order_items/request/" + product_id + "/" + id;

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate.exchange(url, HttpMethod.GET, entity, int[].class).getBody();
	}

	public Boolean getOtherItems(int id, String token) {
		String url = "http://localhost:8082/order_items/requestOtherItems/" + id;

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class).getBody();
	}

	public Boolean checkOtherItemsExist(int product_id, int id, String token) {
		String url = "http://localhost:8082/order_items/checkOtherItemsExist/" + product_id + "/" + id;

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class).getBody();
	}
}
