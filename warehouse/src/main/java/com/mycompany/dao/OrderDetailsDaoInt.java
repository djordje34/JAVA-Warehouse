/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.OrderDetails;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Djordje
 */
public interface OrderDetailsDaoInt {
    void create(Connection con, OrderDetails orderDetails) throws SQLException;

    void update(Connection con, OrderDetails orderDetails) throws SQLException;

    void delete(Connection con, int orderDetailsId) throws SQLException;

    OrderDetails find(Connection con, int orderDetailsId) throws SQLException;

    List<OrderDetails> findAll(Connection con) throws SQLException;
}
