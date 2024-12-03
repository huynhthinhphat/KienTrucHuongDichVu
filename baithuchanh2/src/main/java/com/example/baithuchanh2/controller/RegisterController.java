package com.example.baithuchanh2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.baithuchanh2.request.UserRequest;
import com.example.baithuchanh2.service.RegisterService;

@RestController
public class RegisterController {

	@Autowired
	private RegisterService RegisterService;

	@PostMapping("/register")
	private String register(@RequestBody UserRequest user) {

		boolean status = RegisterService.registerUser(user.getUsername(), user.getPassword());

		if (status) {
			return "Đăng ký thành công";
		} else {
			return "Tài khoản đã tồn tại";
		}
	}
}
