package com.example.baithuchanh4.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.baithuchanh4.entities.OtherItems;
import com.example.baithuchanh4.response.OtherItemsResponse;

public interface OtherItemsRepository extends JpaRepository<OtherItems, Integer> {
	
	@Query("SELECT new com.example.baithuchanh4.response.OtherItemsResponse(o.id, o.order.id, o.product_id, o.product_name, o.quantity, o.unit_price, o.total_price) FROM OtherItems o")
	List<OtherItemsResponse> findAllOtherItems();
	
	@Query("SELECT new com.example.baithuchanh4.response.OtherItemsResponse(o.id, o.order.id, o.product_id, o.product_name, o.quantity, o.unit_price, o.total_price) FROM OtherItems o WHERE o.product_id = :product_id")
	Optional<OtherItemsResponse> findByProductId(@Param("product_id") int product_id);

	@Query("SELECT new com.example.baithuchanh4.response.OtherItemsResponse(o.id, o.order.id, o.product_id, o.product_name, o.quantity, o.unit_price, o.total_price) FROM OtherItems o WHERE o.order.id = :order_id")
	List<OtherItemsResponse> findByOrderId(@Param("order_id") int orderId);
	
	@Query("SELECT SUM(o.total_price) FROM OtherItems o WHERE o.order.id = :id")
	BigDecimal getTotalAmount(@Param("id") int id);
}
