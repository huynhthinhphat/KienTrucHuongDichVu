package com.example.baithuchanh6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baithuchanh6.entities.ProductReports;
import com.example.baithuchanh6.entities.ProductReportsResponse;
import com.example.baithuchanh6.service.MainService;

@RestController
@RequestMapping("/reports/products")
public class ReportProductsController {
	@Autowired
	private MainService service;
	
	@PostMapping()
	public String addReportsProduct(@RequestBody ProductReports productReports, @RequestHeader("Authorization") String token ) {
		return service.addProductReports(productReports, token);
	}
	
	@GetMapping()
	public List<ProductReportsResponse> getReportsProduct(@RequestHeader("Authorization") String token) {
		return service.getReportsProduct(token);
	}

	@GetMapping("/{id}")
	public ProductReportsResponse getReportsProductById(@PathVariable int id,
			@RequestHeader("Authorization") String token) {
		return service.getProductReportsById(id, token);
	}
}
