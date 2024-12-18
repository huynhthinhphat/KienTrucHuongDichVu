package com.example.baithuchanh6.entities;

import java.math.BigDecimal;

public class ProductReports {

	private int id;
	private int order_report;
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

	public int getOrder_report() {
		return order_report;
	}

	public void setOrder_report(int order_report) {
		this.order_report = order_report;
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
