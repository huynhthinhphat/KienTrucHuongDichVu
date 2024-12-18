package com.example.baithuchanh6.entities;

import java.math.BigDecimal;

public class ProductReportsResponse {

	private int id;
	private OrdersReports order_report_id;
	private int product_id;
	private int total_sold;
	private BigDecimal revenue;
	private BigDecimal cost;
	private BigDecimal profit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdersReports getOrder_report() {
		return order_report_id;
	}

	public void setOrder_report(OrdersReports order_report_id) {
		this.order_report_id = order_report_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getTotal_sold() {
		return total_sold;
	}

	public void setTotal_sold(int total_sold) {
		this.total_sold = total_sold;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
}
