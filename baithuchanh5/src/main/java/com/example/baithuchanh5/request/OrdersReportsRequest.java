package com.example.baithuchanh5.request;

import java.math.BigDecimal;

public class OrdersReportsRequest {
	
	private int id;
	private int order_id;
	private BigDecimal total_revenu;
	private BigDecimal total_cost;
	private BigDecimal total_profit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public BigDecimal getTotal_revenu() {
		return total_revenu;
	}

	public void setTotal_revenu(BigDecimal total_revenu) {
		this.total_revenu = total_revenu;
	}

	public BigDecimal getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(BigDecimal total_cost) {
		this.total_cost = total_cost;
	}

	public BigDecimal getTotal_profit() {
		return total_profit;
	}

	public void setTotal_profit(BigDecimal total_profit) {
		this.total_profit = total_profit;
	}
}
