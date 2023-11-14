/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Djordje
 */
public class CustomerDao {
    private static final CustomerDao instance = new CustomerDao();
    
    private CustomerDao(){
    }
    
    public static CustomerDao getInstance() {
        return instance;
    }
    
}
