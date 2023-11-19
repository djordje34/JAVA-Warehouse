/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Order;
import com.mycompany.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Djordje
 */
public interface OrderDaoInt {
    void create(Connection con, Order order) throws SQLException;

    void update(Connection con, Order order) throws SQLException;

    void delete(Connection con, int orderId) throws SQLException;

    Order find(Connection con, int orderId) throws SQLException;

    List<Order> findAll(Connection con) throws SQLException;
}
