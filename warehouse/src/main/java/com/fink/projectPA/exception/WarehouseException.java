/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.exception;

/**
 *
 * @author Djordje
 */
public class WarehouseException extends Exception {

    public WarehouseException(String message) {
        super("-----WAREHOUSE DATABASE OPERATION ERROR-----\n Service layer error: " + message);
    }

    public WarehouseException(String message, Throwable cause) {
        super("-----WAREHOUSE DATABASE OPERATION ERROR-----\n Service layer error: " + message, cause);
    }

}
