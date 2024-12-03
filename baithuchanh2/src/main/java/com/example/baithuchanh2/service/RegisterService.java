package com.example.baithuchanh2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.baithuchanh2.entities.User;
import com.example.baithuchanh2.repository.UserRepository;

@Service
public class RegisterService {

	@Autowired
	private UserRepository repo;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public boolean registerUser(String username, String password) {
		
		Optional<User> checkUserExist = repo.findByUsername(username);
		
		if(!checkUserExist.isPresent()) {
			// Mã hóa mật khẩu
			String encodedPassword = passwordEncoder.encode(password);

			User u = new User();

			u.setUsername(username);
			u.setPassword(encodedPassword);

			repo.save(u);
			
			Optional<User> status = repo.findByUsername(username);
			
			if (status.isPresent()) {
				return true;
			} 
			return false;
		}
		return false;
	}
}
