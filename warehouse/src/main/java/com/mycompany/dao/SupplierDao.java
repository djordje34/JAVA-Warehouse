/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class SupplierDao{
    private static SupplierDao instance = new SupplierDao();
    
    private SupplierDao(){
    
}
    public static SupplierDao getInstance(){
        return instance;
    }
    
    public Supplier find(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try {
            ps = con.prepareStatement("SELECT"
                    + " * "
                    + "FROM "
                    + "suppliers "
                    + "WHERE "
                    + "SupplierId=?");
            ps.setInt(1, dataObjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                supplier = new Supplier(rs.getInt("SupplierId"), rs.getString("SupplierName"), rs.getString("ContactPerson"),
                        rs.getString("Address"),rs.getString("City"),
                        rs.getInt("PostCode"), rs.getString("Country"),rs.getString("Phone"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }
    
    public void delete(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("DELETE "
                    + "FROM "
                    + "suppliers "
                    + "WHERE "
                    + "SupplierId=?");
            ps.setInt(1, dataObjectId);
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void update(Connection con, Supplier supplier) throws SQLException{
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement("UPDATE "
                    + "suppliers"
                    + " SET SupplierName=?, ContactPerson=?, Address=?, "
                    + "City=?, PostCode=?, Country=?, Phone=? "
                    + "WHERE "
                    + "SupplierId=?");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPostCode());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.setInt(8, supplier.getSupplierId());
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void create(Connection con, Supplier supplier) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement("INSERT INTO"
                    + " suppliers(SupplierName, ContactPerson, Address, City,"
                        + "PostCode, Country, Phone) "
                    + "VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContactPerson());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setInt(5, supplier.getPostCode());
            ps.setString(6, supplier.getCountry());
            ps.setString(7, supplier.getPhone());
            ps.executeUpdate();
        }
        finally{
            ResourcesManager.closeResources(rs, ps);
        }
    }
    
    public List<Supplier> findAll(Connection con) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <Supplier> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT "
                            + "* "
                            + "FROM "
                            + "suppliers");
                   
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        l.add(new Supplier(rs.getInt("SupplierId"), rs.getString("SupplierName"), 
                                rs.getString("ContactPerson"), rs.getString("Address"),
                                rs.getString("City"), rs.getInt("PostCode"),rs.getString("Country"),
                                rs.getString("Phone")));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
    
    public Supplier findMostProfitable(Connection con) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier supplier = null;
        try{
            ps = con.prepareStatement("SELECT " +
                        "  s.*, " +
                        "SUM(p.PricePerUnit * od.Quantity) AS uk_pr_cena " +
                        "FROM " +
                        "suppliers s " +
                        "JOIN " +
                        "products p "
                    + "ON "
                    + "s.SupplierId = p.SupplierId " +
                        "JOIN " +
                        "Warehouse.OrderDetails od "
                    + "ON "
                    + "p.ProductId = od.ProductId " +
                        "GROUP BY " +
                        "s.SupplierId, s.SupplierName " +
                        "ORDER BY " +
                        "uk_pr_cena DESC " +
                        "LIMIT 1;");
            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Supplier Id: "+rs.getInt("SupplierId")+" Profit made: " + rs.getInt("uk_pr_cena"));
                supplier = new Supplier(rs.getInt("SupplierId"), rs.getString("SupplierName"), 
                                rs.getString("ContactPerson"), rs.getString("Address"),
                                rs.getString("City"), rs.getInt("PostCode"),rs.getString("Country"),
                                rs.getString("Phone"));
            }
        }
        finally{
            ResourcesManager.closeResources(rs, ps);
        }
        return supplier;
    }
    
    
}
