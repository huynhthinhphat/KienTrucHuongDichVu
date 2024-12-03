package com.example.baithuchanh3.request;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.*;

public class ProductRequest {

	private int id;

	private String name;

	private String description;

	private BigDecimal price;

	private int quantity;

	private Timestamp created_at;

	private Timestamp updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at() {
		this.created_at = new Timestamp(System.currentTimeMillis());
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at() {
		this.updated_at = new Timestamp(System.currentTimeMillis());
	}
}
