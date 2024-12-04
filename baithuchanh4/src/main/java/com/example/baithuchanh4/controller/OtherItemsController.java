package com.example.baithuchanh4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.baithuchanh4.entities.Orders;
import com.example.baithuchanh4.entities.OtherItems;
import com.example.baithuchanh4.request.OtherItemsRequest;
import com.example.baithuchanh4.request.ProductsRequest;
import com.example.baithuchanh4.response.OtherItemsResponse;
import com.example.baithuchanh4.response.ProductsResponse;
import com.example.baithuchanh4.service.OrdersService;
import com.example.baithuchanh4.service.OtherItemsService;
import com.example.baithuchanh4.service.RequestOtherPortService;

@RestController
@RequestMapping("/order_items")
public class OtherItemsController {

	@Autowired
	private OtherItemsService otherItemsService;

	@Autowired
	private RequestOtherPortService requestOtherPortService;

	@GetMapping()
	private List<OtherItemsResponse> getAllOtherItems(@RequestHeader("Authorization") String token) {
		requestOtherPortService.checkAuthentication(token);
		return otherItemsService.getAllOtherItems();
	}

	@GetMapping("/{id}")
	private Optional<OtherItems> getOtherItemsById(@PathVariable int id, @RequestHeader("Authorization") String token) {
		requestOtherPortService.checkAuthentication(token);
		return otherItemsService.getOtherItemsById(id);
	}

	@PostMapping()
	private ResponseEntity<String> addOtherItems(@RequestBody OtherItemsRequest otherItemRequest,
			@RequestHeader("Authorization") String token) {

		requestOtherPortService.checkAuthentication(token);

		ProductsResponse productsResponse = requestOtherPortService
				.getProductById(otherItemRequest.getProduct_id(), token).orElse(null);

		if (productsResponse != null && productsResponse.getQuantity() >= otherItemRequest.getQuantity()) {
			try {
				Boolean result = otherItemsService.addNewOtherItems(productsResponse, otherItemRequest);

				if (result) {

					// trừ số lượng sản phẩm tồn kho
					productsResponse.setQuantity(productsResponse.getQuantity() - otherItemRequest.getQuantity());

					Boolean status = requestOtherPortService.minusOrAddQuantity(productsResponse.getId(), token,
							productsResponse);

					if (status) {
						return ResponseEntity.ok("Thêm thành công");
					}
					return ResponseEntity.status(400).body("Không thành công");

				} else {
					return ResponseEntity.status(400).body("Không thành công");
				}
			} catch (Exception e) {
				return ResponseEntity.internalServerError().body(e.getMessage());
			}
		} else {
			return ResponseEntity.status(400).body("Số lượng hàng tồn kho không đủ");
		}

	}

	@PutMapping("/{id}")
	private ResponseEntity<String> updateOtherItems(@PathVariable int id,
			@RequestBody OtherItemsRequest otherItemRequest, @RequestHeader("Authorization") String token) {

		requestOtherPortService.checkAuthentication(token);

		OtherItems otherItem = otherItemsService.getOtherItemsById(id).orElse(null);

		// quantity product lúc đầu
		int quantityPre = otherItemsService.quantityProduct(otherItem.getProduct_id());

		// gửi request tới bth3 để lấy đối tượng product
		ProductsResponse productsResponse = requestOtherPortService.getProductById(otherItem.getProduct_id(), token)
				.orElse(null);

		// kiểm tra object != null và quantity của object response > quantity của object
		// request
		if (productsResponse != null && productsResponse.getQuantity() >= otherItemRequest.getQuantity()) {
			try {
				Boolean result = otherItemsService.updateOtherItems(id, productsResponse, otherItemRequest);

				// quantity product lúc sau
				int quantityLast = otherItemsService.quantityProduct(otherItem.getProduct_id());

				// nếu quantity product lúc đầu > quantity product lúc sau thì cộng quantity
				// product tồn kho
				if (quantityPre > quantityLast) {
					productsResponse.setQuantity(productsResponse.getQuantity() + (quantityPre - quantityLast));
				} else if (quantityPre < quantityLast) {
					productsResponse.setQuantity(productsResponse.getQuantity() - (quantityLast - quantityPre));
				}
				
				Boolean status = requestOtherPortService.minusOrAddQuantity(productsResponse.getId(), token,
						productsResponse);

				if (result && status) {
					return ResponseEntity.ok("Cập nhật thành công");
				}
				return ResponseEntity.status(400).body("Không thành công");
			} catch (Exception e) {
				return ResponseEntity.internalServerError().body("Lỗi: " + e.getMessage());
			}
		} else {
			return ResponseEntity.status(400).body("Số lượng hàng tồn kho không đủ");
		}
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<String> deleteOtherItems(@PathVariable int id,
			@RequestHeader("Authorization") String token) {

		requestOtherPortService.checkAuthentication(token);

		try {
			// lấy quantity từ object otherItem bị xóa
			OtherItems otherItem = otherItemsService.getOtherItemsById(id).orElse(null);

			ProductsResponse productResponse = requestOtherPortService.getProduct(otherItem.getProduct_id(), token).orElse(null);

			ProductsRequest product = new ProductsRequest();
			product.setQuantity(productResponse.getQuantity() + otherItem.getQuantity());

			Boolean result = otherItemsService.deleteOtherItems(id);

			Boolean status = requestOtherPortService.minusOrAddQuantity(otherItem.getProduct_id(), token, product);

			if (result && status) {
				return ResponseEntity.ok("Xóa thành công");
			} else {
				return ResponseEntity.status(400).body("Không thành công");
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Lỗi: " + e.getMessage());
		}
	}
}
