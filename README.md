# JAVA Warehouse

## Introduction

The Warehouse Management System is designed to streamline the process of managing orders, products, and suppliers in a warehouse environment. It provides features to handle customer orders, track products, and manage supplier/shipper information.

## Features

- Customer, employee and order management
- Product, order and order details tracking
- Supplier and shipper information management

## Prerequisites

- JDK 11
- Payara Server
- Java EE 8 Web
- MySQL database

## Cloning the repository

Cloning the repository:
   ```bash
   git clone https://github.com/djordje34/JAVA-Warehouse.git
```

## Usage
To use the Warehouse Management System, navigate to [http://localhost:8080/warehouse](http://localhost:8080/warehouse) in your web browser.

## API Endpoints
Some of the API endpoints defined in the Warehouse Management Project:

### /rest/customers: Manage customers
- **GET /rest/customers:** Get all customers
- **GET /rest/customers/{customerId}:** Get customer by ID
- **POST /rest/customers:** Create a new customer
- **PUT /rest/customers/{customerId}:** Update customer parameters
- **DELETE /rest/customers/{customerId}:** Delete a customer

### /rest/customers: Manage employees
- **GET /rest/employees:** Get all employees
- **GET /rest/employees/{employeeId}:** Get employee by ID
- **POST /rest/employees:** Create a new employee
- **PUT /rest/employees/{employeeId}:** Update employee parameters
- **DELETE /rest/employees/{employeeId}:** Delete an employee

### /rest/products: Manage products
- **GET /rest/products:** Get all products
- **GET /rest/products/{productId}:** Get product by ID
- **POST /rest/products:** Create a new product
- **PUT /rest/products/{productId}:** Update product parameters
- **DELETE /rest/products/{productId}:** Delete a product

### /rest/suppliers: Manage suppliers
- **GET /rest/suppliers:** Get all suppliers
- **GET /rest/suppliers/{supplierId}:** Get supplier by ID
- **POST /rest/suppliers:** Create a new supplier
- **PUT /rest/suppliers/{supplierId}:** Update supplier parameters
- **DELETE /rest/suppliers/{supplierId}:** Delete a supplier

### /rest/shippers: Manage shippers
- **GET /rest/shippers:** Get all shippers
- **GET /rest/shippers/{shipperId}:** Get shipper by ID
- **POST /rest/shippers:** Create a new shipper
- **PUT /rest/shippers/{shipperId}:** Update shipper's parameters
- **DELETE /rest/shippers/{shipperId}:** Delete a shipper

### /rest/orders: Manage orders
- **GET /rest/orders:** Get all orders
- **GET /rest/orders/{orderId}:** Get order by ID
- **POST /rest/orders:** Create a new order
- **PUT /rest/orders/{orderId}:** Update order parameters
- **DELETE /rest/orders/{orderId}:** Delete an order

### /rest/orderdetails: Manage order details
- **GET /rest/orderdetails:** Get all order details
- **GET /rest/orderdetails/{orderDetailsId}:** Get order details by ID
- **POST /rest/orderdetails:** Create new order details
- **PUT /rest/orderdetails/{orderDetailsId}:** Update order details parameters
- **DELETE /rest/orderdetails/{orderDetailsId}:** Delete an order details item

## Examples
Here are a few examples to demonstrate how to use the Warehouse Management System:

- **Creating a new customer:**
  Send a POST request to `/rest/customers` with customer parameters.
- **Adding a new product:**
  Send a POST request to `/rest/products` with product parameters.
- **Placing a new order:**
  Send a POST request to `/rest/orders` with order parameters.
