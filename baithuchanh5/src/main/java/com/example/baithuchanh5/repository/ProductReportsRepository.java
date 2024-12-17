package com.example.baithuchanh5.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.baithuchanh5.entities.ProductReports;
import com.example.baithuchanh5.response.ProductReportsResponse;

@Repository
public interface ProductReportsRepository extends JpaRepository<ProductReports, Integer> {

	@Query("SELECT new com.example.baithuchanh5.response.ProductReportsResponse(pr.id, pr.order_report.id, pr.product_id, pr.total_sold, pr.revenue, pr.cost, pr.profit) FROM ProductReports pr WHERE pr.product_id = :product_id")
	List<ProductReportsResponse> findProductReportsByProductId(@Param("product_id") int product_id);
	
	@Query("SELECT SUM(pr.revenue) FROM ProductReports pr WHERE order_report.id = :id")
	BigDecimal sumTotalRevenue(@Param("id") int id);
	
	@Query("SELECT SUM(pr.cost) FROM ProductReports pr WHERE order_report.id = :id")
	BigDecimal sumTotalCost(@Param("id") int id);

	@Query("SELECT pr FROM ProductReports pr WHERE product_id = :product_id AND order_report.id = :order_report_id")
	Optional<ProductReports> findProductReportByProductIdAndOrderReportId(@Param("product_id") int product_id, @Param("order_report_id") int order_report_id);
	
	@Query("SELECT pr FROM ProductReports pr WHERE product_id = :product_id")
	List<ProductReports> findProductReportsByProductIdFromOrdersReport(@Param("product_id") int product_id);
}
