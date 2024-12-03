package com.example.baithuchanh2.entities;

import jakarta.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;

	@Column(nullable = true, columnDefinition = "varchar(255)")
	private String username;

	@Column(nullable = true, columnDefinition = "varchar(255)")
	private String password;

	@Column(nullable = true, columnDefinition = "varchar(255)")
	private String token;

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}