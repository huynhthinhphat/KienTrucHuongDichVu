package com.example.baithuchanh6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.baithuchanh6.entities.OrdersReports;
import com.example.baithuchanh6.service.MainService;

@RestController
@RequestMapping("/reports/orders")
public class ReportsOrdersController {

	@Autowired
	private MainService service;

	@PostMapping()
	public String addReportsOrders(@RequestBody OrdersReports ordersReport,
			@RequestHeader("Authorization") String token) {
		return service.addReportsOrders(ordersReport, token);
	}

	@GetMapping()
	public List<OrdersReports> getReportsOrders(@RequestHeader("Authorization") String token) {
		return service.getReportsOrders(token);
	}

	@GetMapping("/{id}")
	public OrdersReports getReportsOrdersById(@PathVariable int id,
			@RequestHeader("Authorization") String token) {
		return service.getReportsOrdersById(id, token);
	}
}
