package com.example.baithuchanh5.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class ProductReports {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "order_report_id", nullable = true, foreignKey = @ForeignKey(name = "fk_order_report_id"))
	private OrdersReports order_report;

	@Column(nullable = true)
	private int product_id;

	@Column(nullable = true)
	private int total_sold;
	
	@Column(nullable = true, length = 10, scale = 2)
	private BigDecimal revenue;
	
	@Column(nullable = true, length = 10, scale = 2)
	private BigDecimal cost;
	
	@Column(nullable = true, length = 10, scale = 2)
	private BigDecimal profit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdersReports getOrder_report() {
		return order_report;
	}

	public void setOrder_report(OrdersReports order_report) {
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

	public void setProfit() {
		this.profit = revenue.subtract(cost);
	}

	public void setTotal_sold(int total_sold) {
		this.total_sold = total_sold;
	}
}
