package com.example.baithuchanh3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestOtherPortService {

	@Autowired
	// RestTemplate là một lớp trong Spring Framework, được sử dụng để thực hiện các yêu cầu HTTP (GET, POST, PUT, DELETE, v.v.) từ ứng dụng Java của bạn tới các dịch vụ web (RESTful APIs)
	// Cần phải cấu hình để có thể sử dụng RestTemplate
	private RestTemplate restTemplate;

	public Boolean auth(String token) {
		String url = "http://localhost:8080/auth/baithuchanh";

		// Tạo HttpHeaders và thêm Authorization header với Bearer token
		// tạo ra một đối tượng HttpHeaders và thiết lập một header HTTP với tên là Authorization và giá trị là token
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token); 

		// Tạo HttpEntity với header
		// Tạo một đối tượng HttpEntity với mục đích đóng gói các thông tin cần thiết (ví dụ: headers, body của yêu cầu) để gửi trong một yêu cầu HTTP
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Gửi yêu cầu GET với RestTemplate
		//gửi một yêu cầu HTTP tới một URL nhất định và nhận phản hồi, sau đó trả về phần thân (body) của phản hồi dưới dạng một đối tượng kiểu Boolean
		// url: địa chỉ endpoint nhận request
		// HttpMethod.GET: gửi yêu cầu GET mà không thay đổi trạng thái hệ thống
		// entity: chứa các thông tin gửi đến endpoint
		// Boolean.class: kiểu dữ liệu mong muốn nhận từ server
		// getbody(): nếu không có getbody thì phản hồi nhận được sẽ là đối tượng ResponseEntity chứa toàn bộ thông tin về phản hồi HTTP (body, headers, status code)
		// khi có getbody thì sẽ lấy mỗi body trong HTTP
		return restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class).getBody();
	}
}
