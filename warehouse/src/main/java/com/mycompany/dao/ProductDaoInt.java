/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Djordje
 */
public interface ProductDaoInt {
    void create(Connection con, Product product) throws SQLException;

    void update(Connection con, Product product) throws SQLException;

    void delete(Connection con, int productId) throws SQLException;

    Product find(Connection con, int orderDetailsId) throws SQLException;

    List<Product> findAll(Connection con) throws SQLException;
}
