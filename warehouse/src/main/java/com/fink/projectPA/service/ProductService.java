/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.ResourcesManager;
import com.fink.projectPA.dao.ProductDao;
import com.fink.projectPA.data.Product;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Djordje
 */
public class ProductService {
    private static final ProductService instance = new ProductService();
    
    private ProductService(){
        
    }
    
    public static ProductService getInstance(){
        return instance;
    }
    
    public void addNewProduct(Product product) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ProductDao.getInstance().create(con, product);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new product " + product, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Product findProduct(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return ProductDao.getInstance().find(con, dataObjectId);
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find product with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteProduct(int dataObjectId) throws WarehouseException{
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Product product = ProductDao.getInstance().find(con, dataObjectId);
            if (product != null) {
                ProductDao.getInstance().delete(con, dataObjectId);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete product with ID " + dataObjectId, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateProduct(Product product) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            ProductDao.getInstance().update(con, product);
            
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Product> findAllProducts() throws WarehouseException{
        Connection con = null;
        List <Product> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = ProductDao.getInstance().findAll(con);
            return l;
        }
        catch(SQLException ex){
            throw new WarehouseException("Failed to find all products", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
}
