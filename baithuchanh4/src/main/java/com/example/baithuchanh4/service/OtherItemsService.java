package com.example.baithuchanh4.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.baithuchanh4.entities.Orders;
import com.example.baithuchanh4.entities.OtherItems;
import com.example.baithuchanh4.repository.OrdersRepository;
import com.example.baithuchanh4.repository.OtherItemsRepository;
import com.example.baithuchanh4.request.OtherItemsRequest;
import com.example.baithuchanh4.response.OtherItemsResponse;

@Service
public class OtherItemsService {

	@Autowired
	private OtherItemsRepository otherItemsRepository;

	@Autowired
	private OrdersRepository ordersRepository;

	public List<OtherItemsResponse> getAllOtherItems() {
		return otherItemsRepository.findAllOtherItems();
	}

	public Optional<OtherItems> getOtherItemsById(int id) {
		return otherItemsRepository.findById(id);
	}

	public Boolean addNewOtherItems(OtherItemsRequest otherItemsRequest) {

		Optional<Orders> o = ordersRepository.findById(otherItemsRequest.getOrder_id());

		OtherItems oi = null;

		if (o.isPresent()) {

			oi = new OtherItems();

			BigDecimal quantity = new BigDecimal(otherItemsRequest.getQuantity());
			BigDecimal unit_price = otherItemsRequest.getUnit_price();
			BigDecimal total_price = getTotalPrice(quantity, unit_price);

			oi.setOrder(o.get());
			oi.setProduct_id(otherItemsRequest.getProduct_id());
			oi.setProduct_name(otherItemsRequest.getProduct_name());
			oi.setQuantity(otherItemsRequest.getQuantity());
			oi.setUnit_price(unit_price);
			oi.setTotal_price(total_price);

			otherItemsRepository.save(oi);

			o.get().setTotal_amount(otherItemsRepository.getTotalAmount(otherItemsRequest.getOrder_id()));

			ordersRepository.save(o.get());
		}

		return otherItemsRepository.save(oi) != null && otherItemsRepository.save(oi).getId() > 0;

	}

	public Boolean updateOtherItems(int id, OtherItemsRequest otherItemsRequest) {

		Optional<OtherItems> o = otherItemsRepository.findById(id);

		Orders order = ordersRepository.findById(otherItemsRequest.getOrder_id()).orElse(null);

		OtherItems statusOtherItem = null;

		if (o.isPresent() && order != null) {

			BigDecimal unit_price = otherItemsRequest.getUnit_price();
			BigDecimal quantity = new BigDecimal(otherItemsRequest.getQuantity());
			BigDecimal total_price = getTotalPrice(quantity, unit_price);

			o.get().setProduct_id(otherItemsRequest.getProduct_id());
			o.get().setProduct_name(otherItemsRequest.getProduct_name());
			o.get().setQuantity(otherItemsRequest.getQuantity());
			o.get().setUnit_price(unit_price);
			o.get().setTotal_price(total_price);

			statusOtherItem = otherItemsRepository.save(o.get());

			order.setTotal_amount(otherItemsRepository.getTotalAmount(otherItemsRequest.getOrder_id()));

			ordersRepository.save(order);
		}

		return statusOtherItem != null && statusOtherItem.getId() > 0;
	}

	private BigDecimal getTotalPrice(BigDecimal quantity, BigDecimal unit_price) {
		return quantity.multiply(unit_price);
	}

	public Boolean deleteOtherItems(int id) {

		OtherItems otherItems = otherItemsRepository.findById(id).orElse(null);

		if (otherItems == null) {
			return false;
		}

		Orders order = ordersRepository.findById(otherItems.getOrder().getId()).orElse(null);

		if (order == null) {
			return false;
		}

		otherItemsRepository.deleteById(id);
		order.setTotal_amount(otherItemsRepository.getTotalAmount(otherItems.getOrder().getId()));

		ordersRepository.save(order);
		
		return true;
	}
}
