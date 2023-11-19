/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Customer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Djordje
 */
public interface CustomerDaoInt {
    void create(Connection con, Customer customer) throws SQLException;

    void update(Connection con, Customer customer) throws SQLException;

    void delete(Connection con, int customerId) throws SQLException;

    Customer find(Connection con, int customerId) throws SQLException;

    List<Customer> findAll(Connection con) throws SQLException;
}
