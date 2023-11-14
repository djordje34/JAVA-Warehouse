/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

/**
 *
 * @author Djordje
 */
public class OrderDetails {
    private int orderDetailsId = -1;
    private Order order;
    private Product product;
    private int quantity;

    public OrderDetails(int orderDetailsId, Order order, Product product, int quantity) {
        this.orderDetailsId = orderDetailsId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderDetailsId=" + orderDetailsId + ", order=" + order + ", product=" + product + ", quantity=" + quantity + '}';
    }
    
    
    
    
}
