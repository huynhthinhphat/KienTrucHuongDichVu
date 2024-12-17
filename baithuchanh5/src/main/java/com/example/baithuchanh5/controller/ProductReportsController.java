package com.example.baithuchanh5.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.baithuchanh5.entities.ProductReports;
import com.example.baithuchanh5.request.ProductReportsRequest;
import com.example.baithuchanh5.service.ProductReportsService;
import com.example.baithuchanh5.service.RequestOtherPortService;

@RestController
@RequestMapping("/reports/products")
public class ProductReportsController {

	@Autowired
	private ProductReportsService productReportsService;

	@Autowired
	private RequestOtherPortService requestOtherPortService;

	@GetMapping()
	public List<ProductReports> findAll() {
		return productReportsService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<ProductReports> findById(@PathVariable int id) {
		return productReportsService.findById(id);
	}

	@PostMapping()
	public String addReportProduct(@RequestBody ProductReportsRequest productReportsRequest,
			@RequestHeader("Authorization") String token) {

		int[] data = requestOtherPortService.getProduct(productReportsRequest.getOrder_report(),
				productReportsRequest.getProduct_id(), token);

		productReportsRequest.setTotal_sold(data[0]);
		productReportsRequest.setRevenue(new BigDecimal(data[1]));

		if (!requestOtherPortService.checkOtherItemsExist(productReportsRequest.getProduct_id(),
				productReportsRequest.getOrder_report(), token)) {
			return "Other Item không tồn tại";
		}

		if (productReportsService.addProductReport(productReportsRequest)) {
			return "Tạo báo cáo sản phẩm thành công";
		}
		return "Tạo báo cáo sản phẩm không thành công";
	}

	@DeleteMapping("/{id}")
	public Boolean deleteProductReports(@PathVariable int id, @RequestHeader("Authorization") String token) {

		if (requestOtherPortService.auth(token)) {
			return productReportsService.deleteProductReport(id);
		}
		return false;
	}

}
