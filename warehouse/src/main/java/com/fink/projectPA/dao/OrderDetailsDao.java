/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.dao;

import com.fink.projectPA.data.Order;
import com.fink.projectPA.data.OrderDetails;
import com.fink.projectPA.data.Product;
import com.fink.projectPA.exception.WarehouseException;
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
public class OrderDetailsDao{
    
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
            ps = con.prepareStatement("SELECT"
                    + " * "
                    + "FROM"
                    + " orderdetails"
                    + " WHERE "
                    + "OrderDetailsId=?");
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
            if (OrderDetailsDao.getInstance().find(con, dataObjectId).getOrder() != null) {
                    OrderDao.getInstance().delete(con, OrderDetailsDao.getInstance().find(con, dataObjectId).getOrder().getOrderId());
            }
            
            ps = con.prepareStatement("DELETE"
                    + " FROM "
                    + "orderdetails "
                    + "WHERE "
                    + "OrderDetailsId=?");
            ps.setInt(1, dataObjectId);
             
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void update(Connection con, OrderDetails orderDetails) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE "
                    + "orderdetails "
                    + "SET "
                    + "OrderId=?, ProductId=?, Quantity=? "
                    + "WHERE"
                    + " OrderDetailsId=?");
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
   
            ps = con.prepareStatement("INSERT INTO "
                    + "orderdetails(OrderId, ProductId, Quantity) "
                    + "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            
            Order order = OrderDao.getInstance().find(con, orderDetails.getOrder().getOrderId());
            if (order == null){
                int orderId = OrderDao.getInstance().create(con, orderDetails.getOrder());
                order = OrderDao.getInstance().find(con,orderId);
            }
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
                    ps = con.prepareStatement("SELECT"
                            + " * "
                            + "FROM "
                            + "orderdetails");
                   
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
                    ps = con.prepareStatement("SELECT " +
                                        "SUM(p.PricePerUnit * od.Quantity) AS uk_cena\n" +
                                        "FROM\n " +
                                        "orderdetails od " +
                                        "JOIN " +
                                        "products p ON od.ProductId = p.ProductId");
                   
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
                    ps = con.prepareStatement("SELECT " +
                            "SUM(p.PricePerUnit * od.Quantity) AS uk_cena " +
                            "FROM " +
                            "customers c " +
                            "JOIN " +
                            "  orders o ON c.CustomerId = o.CustomerId " +
                            "JOIN " +
                            "orderdetails od ON o.OrderId = od.OrderId " +
                            "JOIN " +
                            "products p ON od.ProductId = p.ProductId " +
                            "WHERE " +
                            "  c.CustomerId=? " +
                            "GROUP BY " +
                            "  c.CustomerId");
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
                    ps = con.prepareStatement("SELECT " +
                                "SUM(p.PricePerUnit * od.Quantity) AS uk_cena " +
                                "FROM " +
                                "shippers s " +
                                "JOIN " +
                                "orders o ON s.ShipperId = o.ShipperId " +
                                "JOIN " +
                                "orderdetails od ON o.OrderId = od.OrderId " +
                                "JOIN " +
                                "  products p ON od.ProductId = p.ProductId " +
                                "WHERE " +
                                "  s.ShipperId=? " +
                                "GROUP BY " +
                                "  s.ShipperId");
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
                    ps = con.prepareStatement("SELECT " +
                    "SUM(p.PricePerUnit * od.Quantity) AS uk_cena " +
                    "FROM " +
                    "suppliers sup " +
                    "JOIN " +
                    "products p ON sup.SupplierId = p.SupplierId " +
                    "JOIN " +
                    "orderdetails od ON p.ProductId = od.ProductId " +
                    "WHERE " +
                    "  sup.SupplierId=? " +
                    "GROUP BY " +
                    "  sup.SupplierId");
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
