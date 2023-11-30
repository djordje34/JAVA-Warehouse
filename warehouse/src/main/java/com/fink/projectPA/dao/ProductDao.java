/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;
import com.fink.projectPA.data.Employee;
import com.fink.projectPA.data.Product;
import com.fink.projectPA.data.Supplier;
import com.fink.projectPA.exception.WarehouseException;
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
public class ProductDao{
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
            ps = con.prepareStatement("SELECT"
                    + " * "
                    + "FROM "
                    + "products "
                    + "WHERE "
                    + "ProductId=?");
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
            ps = con.prepareStatement("DELETE "
                    + "FROM "
                    + "products "
                    + "WHERE "
                    + "ProductId=?");
            ps.setInt(1, dataObjectId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    
        public void update(Connection con, Product product) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE "
                    + "products "
                    + "SET "
                    + "ProductName=?, "
                    + "ProductCategory=?, "
                    + "PricePerUnit=?, "
                    + "SupplierId=? "
                    + "WHERE "
                    + "ProductId=?");
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
    
    public void create(Connection con, Product product) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO "
                    + "products(ProductName, "
                    + "ProductCategory, PricePerUnit, "
                    + "SupplierId) "
                    + "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductCategory());
            ps.setInt(3, product.getPricePerUnit());
            Supplier supplier = SupplierDao.getInstance().find(con, product.getSupplier().getSupplierId());
            if (supplier == null) {
                throw new SQLException("Supplier " + product.getSupplier() + " doesn't exist in database.");
            }
            ps.setInt(4, supplier.getSupplierId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }
    
    
    public List<Product> findAll(Connection con) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Product> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT"
                                            + " * "
                                            + "FROM products");
                   
                    rs = ps.executeQuery();
                    while (rs.next()) {
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

    public List<Product> findAllFromSupplier(Connection con,int dataObjectId) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Product> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT DISTINCT " +
                                                " * " +
                                                "FROM " +
                                                "products " +
                                                "WHERE " +
                                                "SupplierId=? ");
                    ps.setInt(1, dataObjectId);
                    rs = ps.executeQuery();
                    while (rs.next()) {
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
    
    
    public List<Product> findAllFromShipper(Connection con,int dataObjectId) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Product> l = new ArrayList<>();
                try{
                        ps = con.prepareStatement("SELECT DISTINCT" +
                            "  p.* " +
                            "FROM" +
                            "  products p " +
                            "JOIN " +
                            "  orderdetails od ON p.ProductId = od.ProductId " +
                            "JOIN " +
                            "  orders o ON od.OrderId = o.OrderId " +
                            "WHERE " +
                            "  o.ShipperId=?");
                    ps.setInt(1, dataObjectId);
                    rs = ps.executeQuery();
                    while (rs.next()) {
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
    
    public List<Product> findTwoMostPopular(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <Product> l = new ArrayList<>();
        try{
            ps = con.prepareStatement("SELECT " +
                    "  p.*, " +
                    "  COUNT(*) AS br_order " +
                    "FROM " +
                    "  products p " +
                    "JOIN " +
                    "  orderdetails od ON p.ProductId = od.ProductId " +
                    "GROUP BY\n" +
                    "  p.ProductId, p.ProductName " +
                    "ORDER BY " +
                    "  br_order DESC " +
                    "LIMIT 2;");
                    rs = ps.executeQuery();
                    while(rs.next()){
                        Supplier supplier = SupplierDao.getInstance().find(con, rs.getInt("SupplierId"));
                        l.add(new Product(rs.getInt("ProductId"), rs.getString("ProductName"), 
                                rs.getString("ProductCategory"), rs.getInt("PricePerUnit"), supplier));
                    System.out.println("ProductId: "+ rs.getInt("ProductId") + " Broj narucivanja: " + rs.getInt("br_order"));
                    }
        }
        finally{
            ResourcesManager.closeResources(rs, ps);
        }
        return l;
    }
    
}
