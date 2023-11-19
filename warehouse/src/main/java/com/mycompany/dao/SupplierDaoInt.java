/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Supplier;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Djordje
 */
public interface SupplierDaoInt {
    void create(Connection con, Supplier supplier) throws SQLException;

    void update(Connection con, Supplier supplier) throws SQLException;

    void delete(Connection con, int supplierId) throws SQLException;

    Supplier find(Connection con, int supplierId) throws SQLException;

    List<Supplier> findAll(Connection con) throws SQLException;
}
