package com.example.baithuchanh3.service;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.baithuchanh3.entities.*;
import com.example.baithuchanh3.repository.*;
import com.example.baithuchanh3.request.ProductRequest;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	public Optional<Product> getById(int id) {
		return repo.findById(id);
	}

	public boolean addNewProduct(ProductRequest req) {

		Product product = new Product();

		product.setName(req.getName());
		product.setDescription(req.getDescription());
		product.setPrice(req.getPrice());
		product.setQuantity(req.getQuantity());
		product.setCreated_at();

		Product saveProduct = repo.save(product);

		return saveProduct != null && saveProduct.getId() > 0;
	}

	public boolean updateProduct(int id, ProductRequest productRequest) {

		Optional<Product> product = getById(id);

		if (product.isPresent()) {

			Product updateProduct = product.get();

			String name = productRequest.getName() != null && !productRequest.getName().isEmpty() ? productRequest.getName() : product.get().getName();
			String description = productRequest.getDescription() != null && !productRequest.getDescription().isEmpty() ? productRequest.getDescription() : product.get().getDescription();
			BigDecimal price = productRequest.getPrice() != null ? productRequest.getPrice() : product.get().getPrice();
			int quantity = productRequest.getQuantity() != 0 ? productRequest.getQuantity() : product.get().getQuantity();

			updateProduct.setName(name);
			updateProduct.setDescription(description);
			updateProduct.setPrice(price);
			updateProduct.setQuantity(quantity);
			updateProduct.setUpdated_at();

			repo.save(updateProduct);

			return true;
		}

		return false;
	}

	public boolean deleteProduct(int id) {

		Optional<Product> product = getById(id);

		if (product.isPresent()) {

			repo.deleteById(id);

			return true;
		}

		return false;
	}
}
