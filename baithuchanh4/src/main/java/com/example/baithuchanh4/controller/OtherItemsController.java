package com.example.baithuchanh4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.baithuchanh4.entities.OtherItems;
import com.example.baithuchanh4.request.OtherItemsRequest;
import com.example.baithuchanh4.response.OtherItemsResponse;
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
		try {
			Boolean result = otherItemsService.addNewOtherItems(otherItemRequest);

			if (result) {
				return ResponseEntity.ok("Thêm thành công");
			} else {
				return ResponseEntity.status(400).body("Không thành công");
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Lỗi: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	private ResponseEntity<String> updateOtherItems(@PathVariable int id,
			@RequestBody OtherItemsRequest otherItemRequest, @RequestHeader("Authorization") String token) {
		requestOtherPortService.checkAuthentication(token);
		try {
			Boolean result = otherItemsService.updateOtherItems(id, otherItemRequest);

			if (result) {
				return ResponseEntity.ok("Cập nhật thành công");
			} else {
				return ResponseEntity.status(400).body("Không thành công");
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Lỗi: " + e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<String> deleteOtherItems(@PathVariable int id,
			@RequestHeader("Authorization") String token) {
		requestOtherPortService.checkAuthentication(token);
		try {
			Boolean result = otherItemsService.deleteOtherItems(id);

			if (result) {
				return ResponseEntity.ok("Xóa thành công");
			} else {
				return ResponseEntity.status(400).body("Không thành công");
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Lỗi: " + e.getMessage());
		}
	}
}
