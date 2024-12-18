package com.example.baithuchanh6.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Orders {

	private int id;
	private String customer_name;
	private String customer_email;
	private BigDecimal total_amount;
	private String status;
	private Timestamp created_at;
	private Timestamp updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
