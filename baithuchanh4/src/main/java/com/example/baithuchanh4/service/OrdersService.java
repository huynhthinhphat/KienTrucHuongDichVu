package com.example.baithuchanh4.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.example.baithuchanh4.entities.Orders;
import com.example.baithuchanh4.entities.OtherItems;
import com.example.baithuchanh4.repository.OrdersRepository;
import com.example.baithuchanh4.repository.OtherItemsRepository;
import com.example.baithuchanh4.request.OrdersRequest;
import com.example.baithuchanh4.response.OtherItemsResponse;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private OtherItemsRepository otherItemsRepository;

	public List<Orders> getAllOrders() {
		return ordersRepository.findAll();
	}

	public List<OtherItemsResponse> getOrderById(int id) {
		return otherItemsRepository.findByOrderId(id);
	}

	public Boolean addNewOrder(OrdersRequest ordersRequest) {

		Orders o = new Orders();

		if (ordersRequest.getCustomer_name() == null || ordersRequest.getCustomer_email() == null) {
			return false;
		}

		o.setCustomer_name(ordersRequest.getCustomer_name());
		o.setCustomer_email(ordersRequest.getCustomer_email());
		o.setTotal_amount(new BigDecimal("0.00"));
		o.setStatus("pending");

		Orders statusOrder = ordersRepository.save(o);

		return statusOrder != null && statusOrder.getId() > 0;

	}

	public Boolean updateStatusOrder(int id, OrdersRequest ordersRequest) {

		Optional<Orders> order = ordersRepository.findById(id);

		if (!ordersRequest.getStatus().equals("complete") && !ordersRequest.getStatus().equals("cancelled")) {
			return false;
		}

		if (order.isPresent()) {

			Orders o = order.get();

			o.setStatus(ordersRequest.getStatus());

			ordersRepository.save(o);

			return true;
		}
		return false;
	}

	public Boolean deleteOrder(int id) {

		Optional<Orders> order = ordersRepository.findById(id);

		if (order.isPresent()) {

			ordersRepository.deleteById(id);

			return true;
		}
		return false;
	}
}
