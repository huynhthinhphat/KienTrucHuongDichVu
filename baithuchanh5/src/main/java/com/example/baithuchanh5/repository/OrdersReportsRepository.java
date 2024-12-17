package com.example.baithuchanh5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.baithuchanh5.entities.OrdersReports;

public interface OrdersReportsRepository extends JpaRepository<OrdersReports, Integer>{

	@Query("SELECT or FROM OrdersReports or WHERE order_id = :order_id")
	OrdersReports getOrdersReports(@Param("order_id") int order_id);
}
