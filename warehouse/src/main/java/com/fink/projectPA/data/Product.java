/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

/**
 *
 * @author Djordje
 */
public class Product {
    private int productId = -1;
    private String productName;
    private String productCategory;
    private int pricePerUnit;
    private Supplier supplier;

    public Product(int productId, String productName, String productCategory, int pricePerUnit, Supplier supplier) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.pricePerUnit = pricePerUnit;
        this.supplier = supplier;
    }

    public Product(){
        
    }
    
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", productCategory=" + productCategory + ", pricePerUnit=" + pricePerUnit + ", supplier=" + supplier + '}';
    }
    //dodato zbog unique prod zadatka
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return productId;
    }
    
}
