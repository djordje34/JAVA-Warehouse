/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.ResourcesManager;
import com.mycompany.dao.SupplierDao;
import com.mycompany.data.Supplier;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class SupplierService {
    private static final SupplierService instance = new SupplierService();
    
    private SupplierService(){
        
    }
    
    public static SupplierService getInstance(){
        return instance;
    }
    
    public void addNewSupplier(Supplier supplier) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            SupplierDao.getInstance().create(con, supplier);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new supplier " + supplier, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Supplier findSupplier(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return SupplierDao.getInstance().find(con, dataObjectId);
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find supplier with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    
    public void deleteSupplier(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Supplier supplier = SupplierDao.getInstance().find(con, dataObjectId);
            if (supplier != null) {
                SupplierDao.getInstance().delete(con, dataObjectId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete supplier with ID " + dataObjectId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateSupplier(Supplier supplier) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            SupplierDao.getInstance().update(con, supplier);
            
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update supplier " + supplier, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Supplier> findAllSuppliers() throws WarehouseException{
        Connection con = null;
        List <Supplier> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = SupplierDao.getInstance().findAll(con);
            return l;
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find all suppliers", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
}
