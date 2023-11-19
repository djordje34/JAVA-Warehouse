/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.EmployeeDao;
import com.mycompany.dao.ResourcesManager;
import com.mycompany.data.Employee;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class EmployeeService {
    private static final EmployeeService instance = new EmployeeService();
    
    private EmployeeService(){
        
    }
    
    public static EmployeeService getInstance(){
        return instance;
    }
    
    public void addNewEmployee(Employee employee) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            EmployeeDao.getInstance().create(con, employee);
            con.commit();
            
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new employee " + employee, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Employee findEmployee(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return EmployeeDao.getInstance().find(con, dataObjectId);
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find employee with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    public void deleteEmployee(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Employee employee = EmployeeDao.getInstance().find(con, dataObjectId);
            if (employee != null) {
                EmployeeDao.getInstance().delete(con, dataObjectId);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete employee with ID " + dataObjectId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateEmployee(Employee employee) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            EmployeeDao.getInstance().update(con, employee);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update employee " + employee, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Employee> findAllEmployees() throws WarehouseException{
        Connection con = null;
        List <Employee> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = EmployeeDao.getInstance().findAll(con);
            return l;
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find all employees", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
}
