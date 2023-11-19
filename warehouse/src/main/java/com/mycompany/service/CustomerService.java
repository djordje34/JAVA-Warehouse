/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.CustomerDao;
import com.mycompany.dao.ResourcesManager;
import com.mycompany.data.Customer;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class CustomerService {
    private static final CustomerService instance = new CustomerService();
    
    private CustomerService(){
        
    }
    
    public static CustomerService getInstance(){
        return instance;
    }
    
    public void addNewCustomer(Customer customer) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            CustomerDao.getInstance().create(con, customer);
            con.commit();
            
            
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new customer " + customer, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Customer findCustomer(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return CustomerDao.getInstance().find(con, dataObjectId);
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find customer with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteCustomer(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Customer customer = CustomerDao.getInstance().find(con, dataObjectId);
            if (customer != null) {
                CustomerDao.getInstance().delete(con, dataObjectId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete customer with ID " + dataObjectId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    public void updateCustomer(Customer customer) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            CustomerDao.getInstance().update(con, customer);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Customer> findAllCustomers() throws WarehouseException{
        Connection con = null;
        List <Customer> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = CustomerDao.getInstance().findAll(con);
            return l;
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find all customers", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    
}
    
