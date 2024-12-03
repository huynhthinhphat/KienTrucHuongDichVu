package com.example.baithuchanh3.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "VARCHAR(255)", nullable = true)
	private String name;
	
	@Column(columnDefinition = "TEXT", nullable = true)
	private String description;
	
	@Column(nullable = true, precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(nullable = true)
	private int quantity;
	
	@Column(nullable = true)
	private Timestamp created_at;
	
	@Column(nullable = true)
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
