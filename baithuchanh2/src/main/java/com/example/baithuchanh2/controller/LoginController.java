package com.example.baithuchanh2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.baithuchanh2.request.UserRequest;
import com.example.baithuchanh2.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	// ResponseEntity dùng để đại diện cho toàn bộ phản hồi HTTP (HTTP response) trong một API. Nó giúp kiểm soát các phần của phản hồi HTTP, bao gồm:
	// Status Code (Mã trạng thái HTTP): chỉ định mã trạng thái HTTP cho phản hồi (ví dụ: 200 OK, 404 Not Found, 500 Internal Server Error).
	// Headers (Tiêu đề HTTP): tùy chỉnh các header của phản hồi (ví dụ: Content-Type, Authorization, Location, v.v.).
	// Body (Nội dung phản hồi): xác định nội dung của phản hồi (chẳng hạn như dữ liệu JSON, văn bản, hình ảnh, v.v.).
	public ResponseEntity<String> login(@RequestBody UserRequest user) {
		String token = loginService.login(user.getUsername(), user.getPassword());

		if(token == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai username hoặc password.");
		}
		
		return ResponseEntity.ok(token);
	}

	@GetMapping("/auth")
    public String helloWorld() {
        return "Hello World";
    }
	
	@GetMapping("/auth/product")
    public boolean productAuth() {
        return true;
    }
}
