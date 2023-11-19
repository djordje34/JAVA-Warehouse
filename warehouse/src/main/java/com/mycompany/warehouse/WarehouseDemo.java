/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.warehouse;

import com.mycompany.data.Customer;
import com.mycompany.data.Employee;
import com.mycompany.data.Product;
import com.mycompany.service.CustomerService;
import com.mycompany.service.EmployeeService;
import com.mycompany.service.OrderDetailsService;
import com.mycompany.service.OrderService;
import com.mycompany.service.ProductService;
import com.mycompany.service.ShipperService;
import com.mycompany.service.SupplierService;
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author Djordje
 */
public class WarehouseDemo {

    private static final ProductService productService = ProductService.getInstance();
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final OrderService orderService = OrderService.getInstance();
    private static final OrderDetailsService orderDetailsService = OrderDetailsService.getInstance();
    private static final EmployeeService employeeService = EmployeeService.getInstance();
    private static final ShipperService shipperService = ShipperService.getInstance();
    private static final SupplierService supplierService = SupplierService.getInstance();
    
    public static void main(String[] args) throws  Exception{
        
        customerService.addNewCustomer(new Customer(-1,"Djordje2","Neko","Nesto","Neki",34227,"Srbija"));
        Customer k1 = customerService.findCustomer(2);
        k1.setCity("Batocina");
        //customerService.updateCustomer(k1);
        //customerService.deleteCustomer(2);
        
        employeeService.addNewEmployee(new Employee(-1,"Jedan","Dva", Date.valueOf("2001-11-19")));
        
    }
    
}
