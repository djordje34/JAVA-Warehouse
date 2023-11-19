/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.ResourcesManager;
import com.mycompany.dao.ShipperDao;
import com.mycompany.data.Shipper;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class ShipperService {
    private static final ShipperService instance = new ShipperService();
    
    private ShipperService(){
        
    }
    
    public static ShipperService getInstance(){
        return instance;
    }
    
    public void addNewShipper(Shipper shipper) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ShipperDao.getInstance().create(con, shipper);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new shipper " + shipper, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Shipper findShipper(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return ShipperDao.getInstance().find(con, dataObjectId);
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find shipper with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteShipper(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Shipper shipper = ShipperDao.getInstance().find(con, dataObjectId);
            if (shipper != null) {
                ShipperDao.getInstance().delete(con, dataObjectId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete shipper with ID " + dataObjectId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateShipper(Shipper shipper) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ShipperDao.getInstance().update(con, shipper);
            
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update shipper " + shipper, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Shipper> findAllShippers() throws WarehouseException{
        Connection con = null;
        List <Shipper> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = ShipperDao.getInstance().findAll(con);
            return l;
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find all shippers", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
}
