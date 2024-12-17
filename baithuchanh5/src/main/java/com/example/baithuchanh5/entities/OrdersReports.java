package com.example.baithuchanh5.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class OrdersReports {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true)
	private int order_id;

	@Column(nullable = true, length = 10, scale = 2)
	private BigDecimal total_revenu;

	@Column(nullable = true, length = 10, scale = 2)
	private BigDecimal total_cost;
	
	@Column(nullable = true, length = 10, scale = 2)
	private BigDecimal total_profit;
	
	@OneToMany(mappedBy = "order_report", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductReports> productReports;

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

	public void setTotal_profit() {
		this.total_profit = total_revenu.subtract(total_cost);
	}
}
