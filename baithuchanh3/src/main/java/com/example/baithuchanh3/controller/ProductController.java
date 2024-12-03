package com.example.baithuchanh3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.baithuchanh3.entities.Product;
import com.example.baithuchanh3.request.ProductRequest;
import com.example.baithuchanh3.service.ProductService;
import com.example.baithuchanh3.service.RequestOtherPortService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private RequestOtherPortService RequestOtherPortService;

	public void checkAuthentication(String token) {
		if (!RequestOtherPortService.auth(token)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
		}
	}

	@GetMapping()
	public List<Product> getAllProduct(@RequestHeader("Authorization") String token) {
		checkAuthentication(token);
		return service.getAllProducts();
	}

	@GetMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable int id, @RequestHeader("Authorization") String token) {
		checkAuthentication(token);
		return service.getById(id);
	}

	@PostMapping()
	public boolean addNewProduct(@RequestBody ProductRequest product, @RequestHeader("Authorization") String token) {
		checkAuthentication(token);
		return service.addNewProduct(product);
	}

	@PutMapping("/{id}")
	public boolean updateProduct(@PathVariable int id, @RequestBody ProductRequest product,
			@RequestHeader("Authorization") String token) {
		checkAuthentication(token);
		return service.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public boolean deleteProduct(@PathVariable int id, @RequestHeader("Authorization") String token) {
		checkAuthentication(token);
		return service.deleteProduct(id);
	}
}
