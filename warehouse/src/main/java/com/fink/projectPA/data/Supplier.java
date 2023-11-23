/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

/**
 *
 * @author Djordje
 */
public class Supplier {
    private int supplierId = -1;
    private String supplierName;
    private String contactPerson;
    private String address;
    private String city;
    private int postCode;
    private String country;
    private String phone;

    public Supplier(int supplierId, String supplierName, String contactPerson, String address, String city, int postCode, String country, String phone) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
        this.phone = phone;
    }
    
    public Supplier(){
        
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
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

    public int getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", supplierName=" + supplierName + ", contactPerson=" + contactPerson + ", address=" + address + ", city=" + city + ", postCode=" + postCode + ", country=" + country + ", phone=" + phone + '}';
    }
    
    
}
