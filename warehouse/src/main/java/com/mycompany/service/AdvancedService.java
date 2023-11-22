/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.CustomerDao;
import com.mycompany.dao.EmployeeDao;
import com.mycompany.dao.OrderDetailsDao;
import com.mycompany.dao.ProductDao;
import com.mycompany.dao.ResourcesManager;
import com.mycompany.data.Employee;
import com.mycompany.data.Product;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Djordje
 */
public class AdvancedService {
    private static final AdvancedService instance = new AdvancedService();
    
    private AdvancedService(){
        
    }
    
    public static AdvancedService getInstance(){
        return instance;
    }
    
    
    public List<String> getAllCustomersWithOrders() throws WarehouseException{ //1
        Connection con = null;
        List<String> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = CustomerDao.getInstance().getAllWithOrders(con);
            con.commit();

        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all customers' orders ", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
    public List<Product> findAllProductsFromSupplier(int dataObjectId) throws WarehouseException{ //2
        Connection con = null;
        List<Product> l =new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l= ProductDao.getInstance().findAllFromSupplier(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all products from supplier with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
    public List<Product> findAllProductsFromShipper(int dataObjectId) throws WarehouseException{ //3
        Connection con = null;
        List<Product> l =new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l= ProductDao.getInstance().findAllFromShipper(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all products from shipper with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
    public int findAggregatedPrice() throws WarehouseException{ //4
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumAllOrdersPrices(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices ", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public int findAggregatedOrderPriceFromCustomer(int dataObjectId) throws WarehouseException{ //5
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumOrderPricesFromCustomer(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from customer with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public int findAggregatedOrderPriceFromShipper(int dataObjectId) throws WarehouseException{ //6
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumOrderPricesFromShipper(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from shipper with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public int findAggregatedOrderPriceFromSupplier(int dataObjectId) throws WarehouseException{ //7
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumOrderPricesFromSupplier(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from supplier with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public Employee findBestSellingEmployee() throws WarehouseException {
        Connection con = null;
        Employee emp = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            emp =  EmployeeDao.getInstance().getBestSelling(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the best selling employee"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return emp;
    }
    
    public List<Product> findTwoMostPopularProducts() throws WarehouseException {
        Connection con = null;
        List <Product> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = ProductDao.getInstance().findTwoMostPopular(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the two best selling products"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
}
