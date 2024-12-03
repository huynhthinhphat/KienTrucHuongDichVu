package com.example.baithuchanh4.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true, columnDefinition = "VARCHAR(255)")
	private String customer_name;

	@Column(nullable = true, columnDefinition = "VARCHAR(255)")
	private String customer_email;

	@Column(nullable = true, precision = 10, scale = 2)
	private BigDecimal total_amount;

	@Column(nullable = true, columnDefinition = "VARCHAR(50)")
	private String status;

	@Column(nullable = true)
	private Timestamp created_at;

	@Column(nullable = true)
	private Timestamp updated_at;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OtherItems> otherItems;

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

	@PrePersist
	public void setCreated_at() {
		this.created_at = new Timestamp(System.currentTimeMillis());
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	@PreUpdate
	public void setUpdated_at() {
		this.updated_at = new Timestamp(System.currentTimeMillis());
	}
}
