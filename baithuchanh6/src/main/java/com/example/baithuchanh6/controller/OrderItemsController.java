package com.example.baithuchanh6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.baithuchanh6.entities.OtherItems;
import com.example.baithuchanh6.service.MainService;

public class OrderItemsController {
	
	@Autowired
	private MainService service;

	@PostMapping("/orders")
	public  ResponseEntity<String> addOtherItems(@RequestBody OtherItems otherItem, @RequestHeader("Authorization") String token) {
		return service.addOrderItem(otherItem, token);
	}
	
	
}
