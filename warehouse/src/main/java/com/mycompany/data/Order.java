/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

import java.util.Date;

/**
 *
 * @author Djordje
 */
public class Order {
    private int orderId = -1;
    private Date orderDate;
    private Shipper shipper;
    private Customer customer;
    private Employee employee;

    public Order(int orderId, Date orderDate, Shipper shipper, Customer customer, Employee employee) {
       this.orderId = orderId;
        this.orderDate = orderDate;
        this.shipper = shipper;
        this.customer = customer;
        this.employee = employee;
    }
    
    public Order(){
        
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", shipper=" + shipper + ", customer=" + customer + ", employee=" + employee + '}';
    }
    
    
    
}
