/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Order;
import com.mycompany.data.OrderDetails;
import com.mycompany.data.Product;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Djordje
 */
public class OrderDetailsDao implements OrderDetailsDaoInt{
    
    private static OrderDetailsDao instance = new OrderDetailsDao();
    
    private OrderDetailsDao(){
        
    }
    public static OrderDetailsDao getInstance(){
        return instance;
    }
    
    
    public OrderDetails find(Connection con, int dataObjectId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetails orderDetails = null;
        try {
            ps = con.prepareStatement("SELECT * FROM orderdetails WHERE OrderDetailsId=?");
            ps.setInt(1, dataObjectId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Order order = OrderDao.getInstance().find(con,rs.getInt("OrderId"));
                Product product = ProductDao.getInstance().find(con, rs.getInt("ProductId"));
                orderDetails = new OrderDetails(rs.getInt("OrderDetailsId"),order,product,rs.getInt("Quantity"));
                
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetails;
    }
    
    public void delete(Connection con, int dataObjectId) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM orderdetails WHERE OrderDetailsId=?");
            ps.setInt(1, dataObjectId);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void update(Connection con, OrderDetails orderDetails) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE orderdetails SET OrderId=?, ProductId=?, Quantity=? WHERE OrderDetailsId=?");
            ps.setInt(1, orderDetails.getOrder().getOrderId());
            ps.setInt(2, orderDetails.getProduct().getProductId());
            ps.setInt(3, orderDetails.getQuantity());
            ps.setInt(4, orderDetails.getOrderDetailsId());
            
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    
    
    public void create(Connection con, OrderDetails orderDetails) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO orderdetails(OrderId, ProductId, Quantity) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            Order order = OrderDao.getInstance().find(con, orderDetails.getOrder().getOrderId());
            Product product = ProductDao.getInstance().find(con, orderDetails.getProduct().getProductId());
            if (order == null) {
                throw new SQLException("Order " + orderDetails.getOrder() + " doesn't exist in database.");
            }
            else if(product == null){
                throw new SQLException("Product " + orderDetails.getProduct() + " doesn't exist in database.");
            }
            
            ps.setInt(1, order.getOrderId());
            ps.setInt(2, product.getProductId());
            ps.setInt(3, orderDetails.getQuantity());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }
    
    
    public List<OrderDetails> findAll(Connection con) throws SQLException{
                PreparedStatement ps = null;
                ResultSet rs = null;
                List <OrderDetails> l = new ArrayList<>();
                try{
                    ps = con.prepareStatement("SELECT * FROM orderdetails");
                   
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Order order = OrderDao.getInstance().find(con, rs.getInt("OrderId"));
                        Product product = ProductDao.getInstance().find(con, rs.getInt("ProductId"));
            
                        l.add(new OrderDetails(rs.getInt("OrderDetailsId"), order, product, rs.getInt("Quantity")));
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return l;
    }
    
    
    public int sumAllOrdersPrices(Connection con) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int res = 0;
        try{
                    ps = con.prepareStatement("SELECT\n" +
                                        " SUM(p.PricePerUnit * od.Quantity) AS uk_cena\n" +
                                        "FROM\n" +
                                        "  orderdetails od\n" +
                                        "JOIN\n" +
                                        "  products p ON od.ProductId = p.ProductId;");
                   
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        res += rs.getInt("uk_cena");
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return res;
    }
    
    //zavrsimetodu
    public int sumOrderPricesFromCustomer(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int uk_cena = 0;
        try{
                    ps = con.prepareStatement("SELECT \n" +
                            "  SUM(p.PricePerUnit * od.Quantity) AS uk_cena\n" +
                            "FROM\n" +
                            "  customers c\n" +
                            "JOIN\n" +
                            "  orders o ON c.CustomerId = o.CustomerId\n" +
                            "JOIN\n" +
                            "  orderdetails od ON o.OrderId = od.OrderId\n" +
                            "JOIN\n" +
                            "  products p ON od.ProductId = p.ProductId\n" +
                            "WHERE\n" +
                            "  c.CustomerId = ?\n" +
                            "GROUP BY\n" +
                            "  c.CustomerId;");
                   ps.setInt(1, dataObjectId);
                    rs = ps.executeQuery();
                    while (rs.next()) {
    
                        uk_cena = rs.getInt("uk_cena");
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return uk_cena;
    }
    
     public int sumOrderPricesFromShipper(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int uk_cena = 0;
        try{
                    ps = con.prepareStatement("SELECT\n" +
                                "  SUM(p.PricePerUnit * od.Quantity) AS uk_cena\n" +
                                "FROM\n" +
                                "  shippers s\n" +
                                "JOIN\n" +
                                "  orders o ON s.ShipperId = o.ShipperId\n" +
                                "JOIN\n" +
                                "  orderdetails od ON o.OrderId = od.OrderId\n" +
                                "JOIN\n" +
                                "  products p ON od.ProductId = p.ProductId\n" +
                                "WHERE\n" +
                                "  s.ShipperId=? \n" +
                                "GROUP BY\n" +
                                "  s.ShipperId;");
                   ps.setInt(1, dataObjectId);
                    rs = ps.executeQuery();
                    while (rs.next()) {
    
                        uk_cena = rs.getInt("uk_cena");
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return uk_cena;
    }
     
     public int sumOrderPricesFromSupplier(Connection con, int dataObjectId) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int uk_cena = 0;
        try{
                    ps = con.prepareStatement("SELECT\n" +
                    "  SUM(p.PricePerUnit * od.Quantity) AS uk_cena\n" +
                    "FROM\n" +
                    "  suppliers sup\n" +
                    "JOIN\n" +
                    "  products p ON sup.SupplierId = p.SupplierId\n" +
                    "JOIN\n" +
                    "  orderdetails od ON p.ProductId = od.ProductId\n" +
                    "WHERE\n" +
                    "  sup.SupplierId=? \n" +
                    "GROUP BY\n" +
                    "  sup.SupplierId;");
                   ps.setInt(1, dataObjectId);
                    rs = ps.executeQuery();
                    while (rs.next()) {
    
                        uk_cena = rs.getInt("uk_cena");
                    }
                }
                finally{
                    ResourcesManager.closeResources(rs, ps);
                }
                return uk_cena;
    }
     
    
    
}
