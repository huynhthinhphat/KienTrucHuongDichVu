package com.example.baithuchanh6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baithuchanh6.entities.Product;
import com.example.baithuchanh6.service.MainService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private MainService service;

	@GetMapping()
	public List<Product> laySP(@RequestHeader("Authorization") String token) {
		return service.getProducts(token);
	}

	@PutMapping("/{id}")
	public Boolean updateProduct(@PathVariable int id, @RequestBody Product product,
			@RequestHeader("Authorization") String token) {
		return service.updateProduct(id, product, token);
	}
	
	
}
