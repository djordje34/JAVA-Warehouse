/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.OrderDao;
import com.fink.projectPA.dao.ResourcesManager;
import java.sql.Connection;
import java.sql.SQLException;
import com.fink.projectPA.data.Order;
import com.fink.projectPA.exception.WarehouseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class OrderService {
    private static final OrderService instance = new OrderService();
    
    private OrderService(){
        
    }
    public static OrderService getInstance(){
        return instance;
    }
    
    public void addNewOrder(Order order) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            OrderDao.getInstance().create(con, order);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new order " + order, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    public Order findOrder(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return OrderDao.getInstance().find(con, dataObjectId);
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find order with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteOrder(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Order order = OrderDao.getInstance().find(con, dataObjectId);
            if (order != null) {
                OrderDao.getInstance().delete(con, dataObjectId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order with ID " + dataObjectId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    public void updateOrder(Order order) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            OrderDao.getInstance().update(con, order);
            
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update order " + order, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    public List<Order> findAllOrders() throws WarehouseException{
        Connection con = null;
        List <Order> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = OrderDao.getInstance().findAll(con);
            return l;
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find all orders", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
}
