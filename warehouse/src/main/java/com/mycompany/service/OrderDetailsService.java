/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.OrderDetailsDao;
import com.mycompany.dao.ResourcesManager;
import com.mycompany.data.OrderDetails;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class OrderDetailsService {
    private static final OrderDetailsService instance = new OrderDetailsService();
    
    private OrderDetailsService(){
        
    }
    public static OrderDetailsService getInstance(){
        return instance;
    }
    
    public void addNewOrder(OrderDetails orderDetails) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            OrderDetailsDao.getInstance().create(con, orderDetails);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new order details " + orderDetails, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public OrderDetails findOrderDetails(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return OrderDetailsDao.getInstance().find(con, dataObjectId);
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find order details with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    public void deleteOrderDetails(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OrderDetails orderDetails = OrderDetailsDao.getInstance().find(con, dataObjectId);
            if (orderDetails != null) {
                OrderDetailsDao.getInstance().delete(con, dataObjectId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order details with ID " + dataObjectId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateOrderDetails(OrderDetails orderDetails) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            OrderDetailsDao.getInstance().update(con, orderDetails);
            
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update order details " + orderDetails, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<OrderDetails> findAllOrderDetails() throws WarehouseException{
        Connection con = null;
        List <OrderDetails> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = OrderDetailsDao.getInstance().findAll(con);
            return l;
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find all order details", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
}
