package com.example.baithuchanh5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.baithuchanh5.entities.OrdersReports;
import com.example.baithuchanh5.entities.ProductReports;
import com.example.baithuchanh5.repository.*;
import com.example.baithuchanh5.request.ProductReportsRequest;

@Service
public class ProductReportsService {

	@Autowired
	private ProductReportsRepository productReportsRepository;

	@Autowired
	private OrdersReportsRepository ordersReportsRepository;

	public List<ProductReports> findAll() {
		return productReportsRepository.findAll();
	}

	public Optional<ProductReports> findById(int id) {
		return productReportsRepository.findById(id);
	}

	public Boolean addProductReport(ProductReportsRequest productReportsRequest) {

		ProductReports productReport = new ProductReports();

		OrdersReports ordersReport = ordersReportsRepository.findById(productReportsRequest.getOrder_report())
				.orElse(null);

		ProductReports productReportExsist = productReportsRepository.findProductReportByProductIdAndOrderReportId(
				productReportsRequest.getProduct_id(), productReportsRequest.getOrder_report()).orElse(null);

		if (productReportExsist == null) {
			productReport.setOrder_report(ordersReport);
			productReport.setProduct_id(productReportsRequest.getProduct_id());
			productReport.setTotal_sold(productReportsRequest.getTotal_sold());
			productReport.setRevenue(productReportsRequest.getRevenue());
			productReport.setCost(productReportsRequest.getCost());
			productReport.setProfit();
		} else {
			return false;
		}

		productReportsRepository.save(productReport);

		ordersReport.setTotal_revenu(productReportsRepository.sumTotalRevenue(productReportsRequest.getOrder_report()));
		ordersReport.setTotal_cost(productReportsRepository.sumTotalCost(productReportsRequest.getOrder_report()));
		ordersReport.setTotal_profit();

		return ordersReportsRepository.save(ordersReport) != null;
	}
	
	public Boolean deleteProductReport(int id) {
		
		Optional<ProductReports> productReportOptional = productReportsRepository.findById(id);
	    if (productReportOptional.isEmpty()) {
	        return false;
	    }
		
		productReportsRepository.deleteById(id);
		
		if(productReportsRepository.existsById(id)) {
			return false;
		}
		
		ProductReports productReport = productReportOptional.get();
	    Optional<OrdersReports> orderReportOptional = ordersReportsRepository.findById(productReport.getOrder_report().getId());

		
		if(!orderReportOptional.isPresent()) {
			return false;
		}
		
		OrdersReports orderReport = orderReportOptional.get();
		
		orderReport.setTotal_revenu(productReportsRepository.sumTotalRevenue(productReport.getOrder_report().getId()));
		orderReport.setTotal_cost(productReportsRepository.sumTotalCost(productReport.getOrder_report().getId()));;
		orderReport.setTotal_profit();
		
		return ordersReportsRepository.save(orderReport) != null;
	}
}
