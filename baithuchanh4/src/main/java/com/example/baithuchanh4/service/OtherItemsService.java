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
import com.example.baithuchanh4.response.ProductsResponse;

@Service
public class OtherItemsService {

	@Autowired
	private OtherItemsRepository otherItemsRepository;

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private RequestOtherPortService requestOtherPortService;

	public List<OtherItemsResponse> getAllOtherItems() {
		return otherItemsRepository.findAllOtherItems();
	}

	public Optional<OtherItems> getOtherItemsById(int id) {
		return otherItemsRepository.findById(id);
	}

	public Boolean addNewOtherItems(ProductsResponse productsResponse, OtherItemsRequest otherItemsRequest) {

		if (otherItemsRequest.getQuantity() < 0) {
			return false;
		}

		Optional<Orders> o = ordersRepository.findById(otherItemsRequest.getOrder_id());

		OtherItems oi = null;

		if (o.isPresent()) {

			oi = new OtherItems();

			BigDecimal quantity = new BigDecimal(otherItemsRequest.getQuantity());
			BigDecimal price;

			if (otherItemsRequest.getUnit_price() != null) {
				price = otherItemsRequest.getUnit_price();
			} else {
				price = productsResponse.getPrice();
			}

			BigDecimal total_price = getTotalPrice(quantity, price);
			
			oi.setQuantity(otherItemsRequest.getQuantity());
			oi.setUnit_price(price);
			oi.setTotal_price(total_price);
			oi.setProduct_id(otherItemsRequest.getProduct_id());
			oi.setProduct_name(productsResponse.getName());
			oi.setOrder(o.get());

			otherItemsRepository.save(oi);

			o.get().setTotal_amount(otherItemsRepository.getTotalAmount(otherItemsRequest.getOrder_id()));

			ordersRepository.save(o.get());
		}

		return otherItemsRepository.save(oi) != null && otherItemsRepository.save(oi).getId() > 0;

	}

	public Boolean updateOtherItems(int id, ProductsResponse productsResponse, OtherItemsRequest otherItemsRequest) {

		if (otherItemsRequest.getQuantity() <= 0) {
			return false;
		}

		// tìm object otheritem theo id để update
		Optional<OtherItems> o = otherItemsRepository.findById(id);

		// tìm object order theo id order_id của object otheritem
		Orders order = ordersRepository.findById(o.get().getOrder().getId()).orElse(null);

		// khởi tạo 1 object otheritem để kiểm tra update thành công hay không
		OtherItems statusOtherItem = null;

		// kiểm tra object otheritem tồn tại và object order != null
		if (o.isPresent() && order != null) {

			BigDecimal price;
			if (otherItemsRequest.getUnit_price() != null) {
				price = otherItemsRequest.getUnit_price();
			} else {
				price = o.get().getUnit_price();
			}

			// ép kiểu dữ liệu của quantity từ int thành BigDecimal
			BigDecimal quantity = new BigDecimal(otherItemsRequest.getQuantity());

			// nhận quantity và unit_price để lấy tổng tiền
			BigDecimal total_price = getTotalPrice(quantity, price);

			if (otherItemsRequest.getQuantity() != 0) {
				o.get().setQuantity(otherItemsRequest.getQuantity());
			}

			o.get().setUnit_price(price);
			o.get().setTotal_price(total_price);

			statusOtherItem = otherItemsRepository.save(o.get());

			order.setTotal_amount(otherItemsRepository.getTotalAmount(o.get().getOrder().getId()));

			ordersRepository.save(order);
		}

		return statusOtherItem != null;
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

	public int quantityProduct(int product_id) {
		return otherItemsRepository.quantityProduct(product_id);
	}

}
