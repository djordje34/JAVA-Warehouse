/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

/**
 *
 * @author Djordje
 */
public class Shipper {
    
    private int shipperId = -1;
    private String shipperName;
    private String phone;

    public Shipper(int shipperId, String shipperName, String phone) {
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.phone = phone;
    }

    public Shipper(){
        
    }
    
    public int getShipperId() {
        return shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getPhone() {
        return phone;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Shipper{" + "shipperId=" + shipperId + ", shipperName=" + shipperName + ", phone=" + phone + '}';
    }
    
    
    
    
}
