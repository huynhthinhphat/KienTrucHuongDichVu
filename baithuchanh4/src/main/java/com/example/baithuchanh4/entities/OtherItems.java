package com.example.baithuchanh4.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class OtherItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = true, foreignKey = @ForeignKey(name = "fk_order_id"))
	private Orders order;

	@Column(nullable = true)
	private int product_id;

	@Column(nullable = true, columnDefinition = "VARCHAR(255)")
	private String product_name;

	@Column(nullable = true)
	private int quantity;

	@Column(nullable = true, precision = 10, scale = 2)
	private BigDecimal unit_price;

	@Column(nullable = true, precision = 10, scale = 2)
	private BigDecimal total_price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}
}
