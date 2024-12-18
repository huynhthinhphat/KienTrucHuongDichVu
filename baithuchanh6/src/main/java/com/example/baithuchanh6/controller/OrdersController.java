package com.example.baithuchanh6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baithuchanh6.entities.*;
import com.example.baithuchanh6.service.MainService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	@Autowired
	private MainService service;

	@PostMapping()
	public Boolean addOrders(@RequestBody Orders orders, @RequestHeader("Authorization") String token) {
		return service.addOrder(orders, token);
	}

	@PutMapping("/{id}")
	public Boolean updateStatus(@PathVariable int id, @RequestBody Orders order,
			@RequestHeader("Authorization") String token) {
		return service.updateStatus(id, order, token);
	}

}
