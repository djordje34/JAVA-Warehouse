# JAVA Warehouse

## Introduction

The Warehouse Management System is designed to streamline the process of managing orders, products, and suppliers in a warehouse environment. It provides features to handle customer orders, track products, and manage supplier information.

## Features

- Customer and order management
- Product tracking
- Supplier information management

## Prerequisites

- Java 8 or higher
- MySQL database

## Cloning

Cloning the repository:
   ```bash
   git clone https://github.com/djordje34/JAVA-Warehouse.git
```

## Usage
To use the Warehouse Management System, navigate to [http://localhost:8080](http://localhost:8080) in your web browser. Log in with your credentials and start managing orders, products, customers, employees, shippers and suppliers.

## API Endpoints
The Warehouse Management System includes the following API endpoints:

### /api/customers: Manage customers
- **GET /api/customers:** Get all customers
- **GET /api/customers/{customerId}:** Get customer by ID
- **POST /api/customers:** Create a new customer
- **PUT /api/customers/{customerId}:** Update customer details
- **DELETE /api/customers/{customerId}:** Delete a customer

### /api/products: Manage products
- **GET /api/products:** Get all products
- **GET /api/products/{productId}:** Get product by ID
- **POST /api/products:** Create a new product
- **PUT /api/products/{productId}:** Update product details
- **DELETE /api/products/{productId}:** Delete a product

### /api/suppliers: Manage suppliers
- **GET /api/suppliers:** Get all suppliers
- **GET /api/suppliers/{supplierId}:** Get supplier by ID
- **POST /api/suppliers:** Create a new supplier
- **PUT /api/suppliers/{supplierId}:** Update supplier details
- **DELETE /api/suppliers/{supplierId}:** Delete a supplier

### /api/orders: Manage orders
- **GET /api/orders:** Get all orders
- **GET /api/orders/{orderId}:** Get order by ID
- **POST /api/orders:** Create a new order
- **PUT /api/orders/{orderId}:** Update order details
- **DELETE /api/orders/{orderId}:** Delete an order

## Examples
Here are a few examples to demonstrate how to use the Warehouse Management System:

- **Creating a new customer:**
  Send a POST request to `/api/customers` with customer details.
- **Adding a new product:**
  Send a POST request to `/api/products` with product details.
- **Placing a new order:**
  Send a POST request to `/api/orders` with order details.
