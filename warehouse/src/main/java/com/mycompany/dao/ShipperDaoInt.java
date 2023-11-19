/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Shipper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Djordje
 */
public interface ShipperDaoInt {
    void create(Connection con, Shipper shipper) throws SQLException;

    void update(Connection con, Shipper shipper) throws SQLException;

    void delete(Connection con, int shipperId) throws SQLException;

    Shipper find(Connection con, int shipperId) throws SQLException;

    List<Shipper> findAll(Connection con) throws SQLException;
}
