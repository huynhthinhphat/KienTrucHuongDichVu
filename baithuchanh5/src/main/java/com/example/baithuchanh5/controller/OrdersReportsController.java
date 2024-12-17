package com.example.baithuchanh5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.baithuchanh5.entities.OrdersReports;
import com.example.baithuchanh5.request.OrdersReportsRequest;
import com.example.baithuchanh5.service.OrdersReportsService;
import com.example.baithuchanh5.service.RequestOtherPortService;

@RestController
@RequestMapping("/reports/orders")
public class OrdersReportsController {

	@Autowired
	private OrdersReportsService ordersReportsService;

	@Autowired
	private RequestOtherPortService requestOtherPortService;

	@GetMapping()
	public List<OrdersReports> findAll() {
		return ordersReportsService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<OrdersReports> findById(@PathVariable int id) {
		return ordersReportsService.findById(id);
	}

	@PostMapping()
	public String addOrdersReports(@RequestBody OrdersReportsRequest ordersReportsRequest,
			@RequestHeader("Authorization") String token) {

		if (!requestOtherPortService.getOtherItems(ordersReportsRequest.getOrder_id(), token)) {
			return "Đơn hàng không tồn tại";
		}

		return ordersReportsService.addOrdersReports(ordersReportsRequest);
	}

	@DeleteMapping("/{id}")
	public String deleteOrdersReport(@PathVariable int id, @RequestHeader("Authorization") String token) {

		if (requestOtherPortService.auth(token)) {
			return ordersReportsService.deleteOrdersReports(id);
		}
		return "Xóa không thành công";

	}
}
