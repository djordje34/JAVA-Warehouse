/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.data.Employee;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Djordje
 */
public interface EmployeeDaoInt {
    void create(Connection con, Employee employee) throws SQLException;

    void update(Connection con, Employee employee) throws SQLException;

    void delete(Connection con, int employeeId) throws SQLException;

    Employee find(Connection con, int employeeId) throws SQLException;

    List<Employee> findAll(Connection con) throws SQLException;
}
