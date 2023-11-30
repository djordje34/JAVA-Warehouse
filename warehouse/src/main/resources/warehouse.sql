-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 30, 2023 at 09:59 PM
-- Server version: 5.7.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warehouse`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `CustomerId` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerName` varchar(45) DEFAULT NULL,
  `ContactPerson` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `PostCode` varchar(45) DEFAULT NULL,
  `Country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CustomerId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerId`, `CustomerName`, `ContactPerson`, `Address`, `City`, `PostCode`, `Country`) VALUES
(1, 'Djordje', 'Neko', 'Nesto', 'Batocina', '34227', 'Srbija'),
(2, 'Customer1', 'Contact1', 'Address1', 'City1', '12345', 'Country1'),
(4, 'Customer2', 'Contact2', 'Address2', 'City2', '67890', 'Country2'),
(7, 'Neko ime 2', 'Neki kontakt 4', 'neka adresa 2', 'Batocina 3', '34001', 'Drzava');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `EmployeeId` int(11) NOT NULL AUTO_INCREMENT,
  `LastName` varchar(45) DEFAULT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  PRIMARY KEY (`EmployeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`EmployeeId`, `LastName`, `FirstName`, `BirthDate`) VALUES
(1, 'Karisic', 'Djordje', '2000-10-18'),
(2, 'Jedan', 'Dva', '2001-11-19'),
(3, 'Last1', 'First1', '1980-01-01'),
(5, 'Last2', 'First2', '1876-12-30');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE IF NOT EXISTS `orderdetails` (
  `OrderDetailsId` int(11) NOT NULL AUTO_INCREMENT,
  `OrderId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `Quantity` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrderDetailsId`),
  KEY `fk_OrderDetails_Orders1_idx` (`OrderId`),
  KEY `fk_OrderDetails_Products1_idx` (`ProductId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orderdetails`
--

INSERT INTO `orderdetails` (`OrderDetailsId`, `OrderId`, `ProductId`, `Quantity`) VALUES
(3, 11, 1, '9'),
(4, 12, 2, '10'),
(7, 13, 1, '5'),
(8, 14, 2, '10'),
(9, 14, 1, '7'),
(10, 15, 5, '44');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `OrderId` int(11) NOT NULL AUTO_INCREMENT,
  `OrderDate` date DEFAULT NULL,
  `ShipperId` int(11) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `EmployeeId` int(11) NOT NULL,
  PRIMARY KEY (`OrderId`),
  KEY `fk_Orders_Shippers_idx` (`ShipperId`),
  KEY `fk_Orders_Customers1_idx` (`CustomerId`),
  KEY `fk_Orders_Employees1_idx` (`EmployeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderId`, `OrderDate`, `ShipperId`, `CustomerId`, `EmployeeId`) VALUES
(11, '2023-11-24', 1, 2, 1),
(12, '2023-11-20', 2, 1, 2),
(13, '2023-10-12', 1, 1, 2),
(14, '2022-12-27', 1, 1, 1),
(15, '2023-11-11', 2, 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `ProductId` int(11) NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(45) DEFAULT NULL,
  `ProductCategory` varchar(45) DEFAULT NULL,
  `PricePerUnit` int(45) DEFAULT NULL,
  `SupplierId` int(11) NOT NULL,
  PRIMARY KEY (`ProductId`),
  KEY `fk_Products_Suppliers1_idx` (`SupplierId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductId`, `ProductName`, `ProductCategory`, `PricePerUnit`, `SupplierId`) VALUES
(1, 'Product1', 'Category16', 11, 1),
(2, 'Product2', 'Category2', 20, 2),
(3, 'Product1', 'Category1', 11, 1),
(4, 'Product12', 'Category66', 50, 1),
(5, 'Product4', 'Category4', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `shippers`
--

DROP TABLE IF EXISTS `shippers`;
CREATE TABLE IF NOT EXISTS `shippers` (
  `ShipperId` int(11) NOT NULL AUTO_INCREMENT,
  `ShipperName` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ShipperId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shippers`
--

INSERT INTO `shippers` (`ShipperId`, `ShipperName`, `Phone`) VALUES
(1, 'Shipper the First', '063845950'),
(2, 'Shipper the Second', '063845951');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `SupplierId` int(11) NOT NULL AUTO_INCREMENT,
  `SupplierName` varchar(45) DEFAULT NULL,
  `ContactPerson` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `PostCode` varchar(45) DEFAULT NULL,
  `Country` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SupplierId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`SupplierId`, `SupplierName`, `ContactPerson`, `Address`, `City`, `PostCode`, `Country`, `Phone`) VALUES
(1, 'Supplier1', 'Contact1', 'Address1', 'City1', '12345', 'Serbia', '0653141592'),
(2, 'Supplier II Supplierson', 'Contact Person2', 'Address765', 'City3', '67890', 'Country2', '0646543210'),
(3, 'Supply1', 'Contact34', 'Address 34, Address', 'City of Somewhere', '34220', 'Zemlja', '0606066000');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `fk_OrderDetails_Orders1` FOREIGN KEY (`OrderId`) REFERENCES `orders` (`OrderId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_OrderDetails_Products1` FOREIGN KEY (`ProductId`) REFERENCES `products` (`ProductId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_Orders_Customers1` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`CustomerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Orders_Employees1` FOREIGN KEY (`EmployeeId`) REFERENCES `employees` (`EmployeeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Orders_Shippers` FOREIGN KEY (`ShipperId`) REFERENCES `shippers` (`ShipperId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_Products_Suppliers1` FOREIGN KEY (`SupplierId`) REFERENCES `suppliers` (`SupplierId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
