/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exception;

/**
 *
 * @author Djordje
 */
public class WarehouseException extends Exception {

    public WarehouseException(String message) {
        super("Warehouse service layer error: "+message);
    }

    public WarehouseException(String message, Throwable cause) {
        super("Warehouse service layer error: "+message, cause);
    }

}
