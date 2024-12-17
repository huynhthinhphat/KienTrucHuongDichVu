package com.example.baithuchanh5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.baithuchanh5.entities.OrdersReports;
import com.example.baithuchanh5.entities.ProductReports;
import com.example.baithuchanh5.repository.*;
import com.example.baithuchanh5.request.OrdersReportsRequest;

@Service
public class OrdersReportsService {

	@Autowired
	private OrdersReportsRepository ordersReportsRepository;
	
	@Autowired
	private ProductReportsRepository productReportsRepository;

	public List<OrdersReports> findAll() {
		return ordersReportsRepository.findAll();
	}

	public Optional<OrdersReports> findById(int id) {
		return ordersReportsRepository.findById(id);
	}

	public String addOrdersReports(OrdersReportsRequest ordersReportsRequest) {

		OrdersReports orderReport = new OrdersReports();
		
		OrdersReports status = ordersReportsRepository.getOrdersReports(ordersReportsRequest.getOrder_id());
		
		if(status != null) {
			return "Báo cáo đơn hàng đã tồn tại";
		}
		
		orderReport.setOrder_id(ordersReportsRequest.getOrder_id());

		return ordersReportsRepository.save(orderReport) != null ? "Tạo báo cáo đơn hàng thành công" : "Báo cáo đơn hàng không tồn tại";
	}
	
	public String deleteOrdersReports(int id) {
		Optional<OrdersReports> o = findById(id);
		
		if(o.isPresent()) {
			ordersReportsRepository.delete(o.get());
			
			List<ProductReports> list = productReportsRepository.findProductReportsByProductIdFromOrdersReport(id);
			
			for(ProductReports p : list) {
				productReportsRepository.delete(p);
			}
			
		}else {
			return "Đơn hàng không tồn tại";
		}
		
		return "Xóa thành công";
	}
}
