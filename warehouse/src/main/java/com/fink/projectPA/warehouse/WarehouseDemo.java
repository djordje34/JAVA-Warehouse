/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.fink.projectPA.warehouse;

import com.fink.projectPA.data.Customer;
import com.fink.projectPA.data.Employee;
import com.fink.projectPA.data.Product;
import com.fink.projectPA.data.Supplier;
import com.fink.projectPA.service.AdvancedService;
import com.fink.projectPA.service.CustomerService;
import com.fink.projectPA.service.EmployeeService;
import com.fink.projectPA.service.OrderDetailsService;
import com.fink.projectPA.service.OrderService;
import com.fink.projectPA.service.ProductService;
import com.fink.projectPA.service.ShipperService;
import com.fink.projectPA.service.SupplierService;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final AdvancedService advancedService = AdvancedService.getInstance();
    public static void main(String[] args) throws  Exception{
        
        ///customerService.addNewCustomer(new Customer(-1,"Djordje2","Neko","Nesto","Neki",34227,"Srbija"));
        //Customer k1 = customerService.findCustomer(2);
        //System.out.println(k1);
        //k1.setCity("Batocina");
        //customerService.updateCustomer(k1);
        //customerService.deleteCustomer(2);
        
        //employeeService.addNewEmployee(new Employee(-1,"Jedan","Dva", Date.valueOf("2001-11-19")));
        System.out.println("\n-------------------SQL SOLUTIONS-------------------\n");
        System.out.println("################## 1");
        List<String> l = new ArrayList<>();
        l = advancedService.getAllCustomersWithOrders();
        System.out.println(l);
        System.out.println("################## 2");
        List <Product> l1 = advancedService.findAllProductsFromSupplier(1);
        for(int i=0;i<l1.size();i++){
            System.out.println(l1.get(i));
        }
        System.out.println("################## 3");
        List <Product> l2 = advancedService.findAllProductsFromShipper(1);
        for(int i=0;i<l2.size();i++){
            System.out.println(l2.get(i));
        }
        System.out.println("################## 4");
        int res =advancedService.findAggregatedPrice();
        System.out.println(res);
        System.out.println("################## 5");
        int res1 = advancedService.findAggregatedOrderPriceFromCustomer(1);
        System.out.println(res1);
        System.out.println("################## 6");
        int res2 = advancedService.findAggregatedOrderPriceFromShipper(1);
        System.out.println(res2);
        System.out.println("################## 7");
        int res3 = advancedService.findAggregatedOrderPriceFromSupplier(2);
        System.out.println(res3);
        System.out.println("################## 8");
        Employee e1 = advancedService.findBestSellingEmployee();
        System.out.println(e1);
        System.out.println("################## 9");
        List<Product> l3 = advancedService.findTwoMostPopularProducts();
        for(int i=0;i<l3.size();i++){
            System.out.println(l3.get(i));
        }
        System.out.println("################## 10");
        List<Customer> l4 = advancedService.findFourBestCustomers();
        for(int i=0;i<l4.size();i++){
            System.out.println(l4.get(i));
        }
        System.out.println("################## 11");
        Supplier sup1 = advancedService.findMostProfitableSupplier();
        System.out.println(sup1);
        
        System.out.println("\n----------------PURE JAVA SOLUTIONS----------------\n");
        
        System.out.println("################## 1.1");
        advancedService.getAllCustomersWithOrdersJ();
        
        System.out.println("################## 2.1");
        advancedService.findAllProductsFromSupplierJ(1);
        
        System.out.println("################## 3.1");
        advancedService.findAllProductsFromShipperJ(1);
        
        System.out.println("################## 4.1");
        advancedService.findAggregatedPriceJ();
        
        System.out.println("################## 5.1");
        advancedService.findAggregatedOrderPriceFromCustomerJ(1);
        
        System.out.println("################## 6.1");
        advancedService.findAggregatedOrderPriceFromShipperJ(1);
        
        System.out.println("################## 7.1");
        advancedService.findAggregatedOrderPriceFromSupplierJ(2);
        
        System.out.println("################## 8.1");
        advancedService.findBestSellingEmployeeJ();
        
        System.out.println("################## 9.1");
        advancedService.findTwoMostPopularProductsJ();
        
        System.out.println("################## 10.1");
        advancedService.findFourBestCustomersJ();
        
        System.out.println("################## 11.1");
        advancedService.findMostProfitableSupplierJ();
        
        System.out.println("####TASK####");
        advancedService.findSupplierShipperProducts(1, 1);
        
    }
    
    
    
}
