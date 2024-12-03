package com.example.baithuchanh3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.baithuchanh3.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
