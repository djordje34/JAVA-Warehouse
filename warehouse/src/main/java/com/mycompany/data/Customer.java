/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

/**
 *
 * @author Djordje
 */
public class Customer {
    private int customerId = -1;
    private String customerName;
    private String contactPerson;
    private String address;
    private String city;
    private int postCode;
    private String Country;

    public Customer(int customerId, String customerName, String contactPerson, String address, String city, int postCode, String Country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactPerson = contactPerson;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.Country = Country;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public long getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    @Override
    public String toString() {
        return "Customers{" + "customerId=" + customerId + ", customerName=" + customerName + ", contactPerson=" + contactPerson + ", address=" + address + ", city=" + city + ", postCode=" + postCode + ", Country=" + Country + '}';
    }
    
    
    
    
}
