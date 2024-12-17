-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 04, 2024 lúc 06:38 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thuchanh5`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders_reports`
--

CREATE TABLE `orders_reports` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `total_cost` decimal(38,2) DEFAULT NULL,
  `total_profit` decimal(38,2) DEFAULT NULL,
  `total_revenu` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `orders_reports`
--

INSERT INTO `orders_reports` (`id`, `order_id`, `total_cost`, `total_profit`, `total_revenu`) VALUES
(1, 1, 100000.00, 100000.00, 100000.00),
(2, 2, 100000.00, 100000.00, 100000.00);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_reports`
--

CREATE TABLE `product_reports` (
  `id` int(11) NOT NULL,
  `cost` decimal(38,2) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `profit` decimal(38,2) DEFAULT NULL,
  `revenue` decimal(38,2) DEFAULT NULL,
  `total_sold` int(11) DEFAULT NULL,
  `order_report_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `product_reports`
--

INSERT INTO `product_reports` (`id`, `cost`, `product_id`, `profit`, `revenue`, `total_sold`, `order_report_id`) VALUES
(1, 100000.00, 1, 100000.00, 100000.00, 100000, 1),
(2, 100000.00, 1, 100000.00, 100000.00, 100000, 2),
(3, 100000.00, 2, 100000.00, 100000.00, 100000, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `orders_reports`
--
ALTER TABLE `orders_reports`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product_reports`
--
ALTER TABLE `product_reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order_report_id` (`order_report_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `orders_reports`
--
ALTER TABLE `orders_reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `product_reports`
--
ALTER TABLE `product_reports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `product_reports`
--
ALTER TABLE `product_reports`
  ADD CONSTRAINT `fk_order_report_id` FOREIGN KEY (`order_report_id`) REFERENCES `orders_reports` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
