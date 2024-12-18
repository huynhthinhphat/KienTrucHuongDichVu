package com.example.baithuchanh6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.example.baithuchanh6.controller.OrderItemsController;
import com.example.baithuchanh6.entities.Orders;
import com.example.baithuchanh6.entities.OrdersReports;
import com.example.baithuchanh6.entities.OtherItems;
import com.example.baithuchanh6.entities.Product;
import com.example.baithuchanh6.entities.ProductReports;
import com.example.baithuchanh6.entities.ProductReportsResponse;

@Controller
public class MainService {

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

	public List<Product> getProducts(String token) {
		String url = "http://localhost:8081/products";

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Product>>() {
		}).getBody();
	}

	public Boolean addOrder(Orders orders, String token) {
		String url = "http://localhost:8082/orders";

		HttpEntity<Orders> entity = new HttpEntity<>(orders, header(token));

		return restTemplate.exchange(url, HttpMethod.POST, entity, Boolean.class).getBody();
	}

	public ResponseEntity<String> addOrderItem(OtherItems otherItems, String token) {
		String url = "http://localhost:8082/order_items";

		HttpEntity<OtherItems> entity = new HttpEntity<>(otherItems, header(token));

		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	}

	public Boolean updateProduct(int id, Product product, String token) {
		String url = "http://localhost:8081/products/" + id;

		HttpEntity<Product> entity = new HttpEntity<>(product, header(token));

		return restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class).getBody();
	}

	public Boolean updateStatus(int id, Orders order, String token) {
		String url = "http://localhost:8082/orders/" + id;

		HttpEntity<Orders> entity = new HttpEntity<>(order, header(token));

		return restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class).getBody();
	}

	public String addReportsOrders(OrdersReports ordersReport, String token) {
		String url = "http://localhost:8083/reports/orders";

		HttpEntity<OrdersReports> entity = new HttpEntity<>(ordersReport, header(token));

		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}

	public String addProductReports(ProductReports productReports, String token) {
		String url = "http://localhost:8083/reports/products";

		HttpEntity<ProductReports> entity = new HttpEntity<>(productReports, header(token));

		return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
	}

	public List<OrdersReports> getReportsOrders(String token) {
		String url = "http://localhost:8083/reports/orders";

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate
				.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<OrdersReports>>() {
				}).getBody();
	}

	public OrdersReports getReportsOrdersById(int id, String token) {
		String url = "http://localhost:8083/reports/orders/" + id;

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate.exchange(url, HttpMethod.GET, entity, OrdersReports.class).getBody();
	}

	public List<ProductReportsResponse> getReportsProduct(String token) {
		String url = "http://localhost:8083/reports/products";

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate
				.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProductReportsResponse>>() {
				}).getBody();
	}

	public ProductReportsResponse getProductReportsById(int id, String token) {
		String url = "http://localhost:8083/reports/products/" + id;

		HttpEntity<String> entity = new HttpEntity<>(header(token));

		return restTemplate.exchange(url, HttpMethod.GET, entity, ProductReportsResponse.class).getBody();
	}
}
