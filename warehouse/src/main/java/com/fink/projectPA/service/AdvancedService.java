/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.service;

import com.fink.projectPA.dao.CustomerDao;
import com.fink.projectPA.dao.EmployeeDao;
import com.fink.projectPA.dao.OrderDao;
import com.fink.projectPA.dao.OrderDetailsDao;
import com.fink.projectPA.dao.ProductDao;
import com.fink.projectPA.dao.ResourcesManager;
import com.fink.projectPA.dao.SupplierDao;
import com.fink.projectPA.data.Customer;
import com.fink.projectPA.data.Employee;
import com.fink.projectPA.data.Order;
import com.fink.projectPA.data.OrderDetails;
import com.fink.projectPA.data.Product;
import com.fink.projectPA.data.Supplier;
import com.fink.projectPA.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Djordje
 */
public class AdvancedService {
    private static final AdvancedService instance = new AdvancedService();
    
    private AdvancedService(){
        
    }
    
    public static AdvancedService getInstance(){
        return instance;
    }
    
    
    public List<String> getAllCustomersWithOrders() throws WarehouseException{ //1
        Connection con = null;
        List<String> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = CustomerDao.getInstance().getAllWithOrders(con);
            con.commit();

        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all customers' orders ", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
    public void getAllCustomersWithOrdersJ() throws WarehouseException{ //1.1
        Connection con = null;
        List<Customer> customers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            customers = CustomerDao.getInstance().findAll(con);
            orders = OrderDao.getInstance().findAll(con);
            con.commit(); 
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all customers' orders ", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        customers.sort(Comparator.comparing(Customer::getCustomerName));

        for (Customer customer : customers) {
            for (Order order : orders) {
                if (order.getCustomer().getCustomerId() == customer.getCustomerId()) {
                    System.out.println("CustomerId: "+ order.getCustomer().getCustomerId()+ "  OrderId: " + order.getOrderId());
                }
            }
        }

    }
    
    
    public List<Product> findAllProductsFromSupplier(int dataObjectId) throws WarehouseException{ //2
        Connection con = null;
        List<Product> l =new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l= ProductDao.getInstance().findAllFromSupplier(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all products from supplier with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
    public void findAllProductsFromSupplierJ(int dataObjectId) throws WarehouseException{ //2.1
        Connection con = null;
        List <Product> products = new ArrayList<>();
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            products = ProductDao.getInstance().findAll(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all products from supplier with ID: "+dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        for(Product prod : products){
                if(prod.getSupplier().getSupplierId() == dataObjectId){
                    System.out.println(prod);
                }
            }
    }
    
    
    public List<Product> findAllProductsFromShipper(int dataObjectId) throws WarehouseException{ //3
        Connection con = null;
        List<Product> l =new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l= ProductDao.getInstance().findAllFromShipper(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all products from shipper with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
    public void findAllProductsFromShipperJ(int dataObjectId) throws WarehouseException{ //3.1
        Connection con = null;
        List<OrderDetails> orderDetails =new ArrayList<>();
        Set<Product> un_prods = new HashSet<Product>(); //da se cuva unique prods
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            for(OrderDetails or_d : orderDetails){
                if(or_d.getOrder().getShipper().getShipperId() == dataObjectId)
                    un_prods.add(ProductDao.getInstance().find(con, or_d.getProduct().getProductId()));
            }
            con.commit(); 
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find all products from shipper with ID " + dataObjectId, ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        for(Product prod : un_prods){
            System.out.println(prod);
        }
    }
    
    public int findAggregatedPrice() throws WarehouseException{ //4
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumAllOrdersPrices(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices ", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public void findAggregatedPriceJ() throws WarehouseException{ //4.1
        Connection con = null;
        List<OrderDetails> orderDetails =new ArrayList<>();
        int res = 0;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            con.commit(); 
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices ", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        
        for(OrderDetails or_d : orderDetails){
            res += or_d.getQuantity() * or_d.getProduct().getPricePerUnit();
        }
        System.out.println(res);
    }
    
    public int findAggregatedOrderPriceFromCustomer(int dataObjectId) throws WarehouseException{ //5
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumOrderPricesFromCustomer(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from customer with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public void findAggregatedOrderPriceFromCustomerJ(int dataObjectId) throws WarehouseException{ //5.1
        Connection con = null;
        
        int res_local = 0;
        List<OrderDetails> orderDetails =new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from customer with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        for(OrderDetails or_d : orderDetails){
            if(or_d.getOrder().getCustomer().getCustomerId() != dataObjectId)
                continue;
            res_local += or_d.getQuantity() * or_d.getProduct().getPricePerUnit();
        }
        System.out.println(res_local);
    }
    
    public int findAggregatedOrderPriceFromShipper(int dataObjectId) throws WarehouseException{ //6
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumOrderPricesFromShipper(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from shipper with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public void findAggregatedOrderPriceFromShipperJ(int dataObjectId) throws WarehouseException{ //6.1
        Connection con = null;
        int res_local = 0;
        List<OrderDetails> orderDetails =new ArrayList<>();
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            con.commit();
        } 
        catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from shipper with ID " + dataObjectId , ex);
        } 
        finally {
            ResourcesManager.closeConnection(con);
        }
        for(OrderDetails or_d : orderDetails){
            if(or_d.getOrder().getShipper().getShipperId() != dataObjectId)
                continue;
            res_local += or_d.getQuantity() * or_d.getProduct().getPricePerUnit();
        }
        System.out.println(res_local);
    }
    
    public int findAggregatedOrderPriceFromSupplier(int dataObjectId) throws WarehouseException{ //7
        Connection con = null;
        int res = 0;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            res =  OrderDetailsDao.getInstance().sumOrderPricesFromSupplier(con, dataObjectId);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from supplier with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return res;
    }
    
    public void findAggregatedOrderPriceFromSupplierJ(int dataObjectId) throws WarehouseException{ //7.1
        Connection con = null;
        int res_local = 0;
        List<OrderDetails> orderDetails =new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to sum all the order prices from supplier with ID " + dataObjectId , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        for(OrderDetails or_d : orderDetails){
            if(or_d.getProduct().getSupplier().getSupplierId() != dataObjectId)
                continue;
            res_local += or_d.getQuantity() * or_d.getProduct().getPricePerUnit();
        }
        System.out.println(res_local);
    }
    
    
    
    public Employee findBestSellingEmployee() throws WarehouseException { //8
        Connection con = null;
        Employee emp = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            emp =  EmployeeDao.getInstance().getBestSelling(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the best selling employee"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return emp;
    }
    
    public void findBestSellingEmployeeJ() throws WarehouseException{ //8.1
        Connection con = null;
        Employee best_e = null;
        int max_val = -1;
        List<OrderDetails> orderDetails =new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            employees = EmployeeDao.getInstance().findAll(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the best selling employee"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        for(Employee emp : employees){
            int sell_val = 0;
            
            for(OrderDetails or_d : orderDetails){
                if(or_d.getOrder().getEmployee().getEmployeeId() != emp.getEmployeeId())
                    continue;
                sell_val += or_d.getQuantity() * or_d.getProduct().getPricePerUnit();
            }
            if(sell_val <= max_val)
                continue;
            max_val = sell_val;
            best_e = emp;
        }
        System.out.println(best_e +" spent => "+ max_val);
    }
    
    
    public List<Product> findTwoMostPopularProducts() throws WarehouseException { //9
        Connection con = null;
        List <Product> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = ProductDao.getInstance().findTwoMostPopular(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the two best selling products"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    
    public void findTwoMostPopularProductsJ() throws WarehouseException{ //9.1
        Connection con = null;
        List<OrderDetails> orderDetails = new ArrayList<>();
        List<Product> res = new ArrayList<>();
        Map<Product, Integer> prod_freq = new HashMap<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            for(OrderDetails or_d : orderDetails){
                Product product = or_d.getProduct();
                prod_freq.put(product, prod_freq.getOrDefault(product, 0) + or_d.getQuantity());
            }
            List<Map.Entry<Product, Integer>> sorted_pfreq = new ArrayList<>(prod_freq.entrySet());
            sorted_pfreq.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            for (int i = 0; i < Math.min(2, sorted_pfreq.size()); i++) {
                Product product = sorted_pfreq.get(i).getKey();
                res.add(product);
            }
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the two best selling products"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        for (Product prod : res) {
            int or_c = prod_freq.get(prod);
            System.out.println(prod + " ordered => " + or_c + " units");
        }
    }   
    
    public List <Customer> findFourBestCustomers() throws WarehouseException{
        Connection con = null;
        List <Customer> l = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            l = CustomerDao.getInstance().getBestCustomers(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the four best customers based on money spent"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return l;
    }
    public void findFourBestCustomersJ() throws WarehouseException{
        Connection con = null;
        List<Customer> customers = new ArrayList<>();
        List<OrderDetails> orderDetails = new ArrayList<>();
        Map<Customer, Integer> customer_spent = new HashMap<>();
        
        List<Customer> res = new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            customers = CustomerDao.getInstance().findAll(con);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the four best customers based on money spent"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        
        for(Customer cust : customers){
            int local_spent = 0;
            for(OrderDetails or_d : orderDetails){
                if(or_d.getOrder().getCustomer().getCustomerId() != cust.getCustomerId())
                    continue;
                local_spent += or_d.getQuantity() * or_d.getProduct().getPricePerUnit();
            }
            customer_spent.put(cust, local_spent);
        }
        
        List<Map.Entry<Customer, Integer>> sorted_customer_spent = new ArrayList<>(customer_spent.entrySet());
        sorted_customer_spent.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        
        for (int i = 0; i < Math.min(4, sorted_customer_spent.size()); i++) {
                Customer customer = sorted_customer_spent.get(i).getKey();
                res.add(customer);
        }
        
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i) + " spent => " + customer_spent.get(res.get(i)) + " currency units");
        }
        
    }
    
    
    public Supplier findMostProfitableSupplier() throws WarehouseException{
        Connection con = null;
        Supplier supplier = null;
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            supplier = SupplierDao.getInstance().findMostProfitable(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the most profitable supplier"  , ex);
        
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        return supplier;
    }
    
    public void findMostProfitableSupplierJ() throws WarehouseException{
        Connection con = null;
        List<OrderDetails> orderDetails = new ArrayList<>();
        List<Supplier> suppliers = new ArrayList<>();
        Supplier bestSupplier = null;
        int max_rev = -1;
        
        try{
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);
            orderDetails = OrderDetailsDao.getInstance().findAll(con);
            suppliers = SupplierDao.getInstance().findAll(con);
            con.commit();
        }
        catch(SQLException ex){
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to find the most profitable supplier"  , ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
        for(Supplier supp : suppliers){
            int local_rev = 0;
            for(OrderDetails or_d : orderDetails){
                if (or_d.getProduct().getSupplier().getSupplierId() == supp.getSupplierId()) {
                    local_rev += or_d.getQuantity() * or_d.getProduct().getPricePerUnit();
                }
            }
            if(local_rev > max_rev){
                max_rev = local_rev;
                bestSupplier = supp;
            }
        }
        System.out.println(bestSupplier + ", with total money generated => " + max_rev);
        
    }
    
    
    
}
