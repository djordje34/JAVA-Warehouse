/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.data.Employee;
import com.mycompany.data.Product;
import com.mycompany.data.Supplier;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Djordje
 */
public class ProductDao {
    private static final ProductDao instance = new ProductDao();
    
    private ProductDao(){
    }
    
    public static ProductDao getInstance(){
        return instance;
    }
    
    public Product find(Connection con, int dataObjectId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM products WHERE ProductId=?");
            ps.setInt(1, dataObjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(con,rs.getInt("SupplierId"));
                product = new Product(rs.getInt("ProductId"), rs.getString("ProductName"), rs.getString("ProductCategory"), rs.getInt("PricePerUnit"), supplier);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }
    
    public void delete(Connection con, int dataObjectId) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM products WHERE ProductId=?");
            ps.setInt(1, dataObjectId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    
        public void update(Connection con, Product product) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE products SET ProductName=?, ProductCategory=?, PricePerUnit=?, SupplierId=? WHERE ProductId=?");
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductCategory());
            ps.setInt(3, product.getPricePerUnit());
            ps.setInt(4, product.getSupplier().getSupplierId());
            ps.setInt(5, product.getProductId());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public int create(Connection con, Product product) throws SQLException, WarehouseException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO products(ProductName, ProductCategory, PricePerUnit, SupplierId) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductCategory());
            ps.setInt(3, product.getPricePerUnit());
            Supplier supplier = SupplierDao.getInstance().find(con, product.getSupplier().getSupplierId());
            if (supplier == null) {
                throw new WarehouseException("Shipper " + product.getSupplier() + " doesn't exist in database.");
            }
            ps.setInt(4, supplier.getSupplierId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    
    public List<Product> findAll(Connection con) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Product> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT * FROM products");
                   
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("SupplierId"));
                        l.add(new Product(rs.getInt("ProductId"), rs.getString("ProductName"), 
                                rs.getString("ProductCategory"), rs.getInt("PricePerUnit"), supplier));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
    
}
