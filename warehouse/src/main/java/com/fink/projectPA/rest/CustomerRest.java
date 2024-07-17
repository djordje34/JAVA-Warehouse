/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

import com.fink.projectPA.data.Customer;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.AdvancedService;
import com.fink.projectPA.service.CustomerService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Djordje
 */
@Path("customers")
public class CustomerRest {
    private final CustomerService customerService = CustomerService.getInstance();
    private final AdvancedService advancedService = AdvancedService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List <Customer> getAllCustomers() throws WarehouseException{
        return customerService.findAllCustomers();
    }
    
    @GET
    @Path("/{CustomerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("CustomerId") int customerId) throws WarehouseException{
        return customerService.findCustomer(customerId);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) throws WarehouseException{
            customerService.addNewCustomer(customer);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) throws WarehouseException {
            customerService.updateCustomer(customer);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{CustomerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("CustomerId") int customerId) throws WarehouseException{
            customerService.deleteCustomer(customerId);
            return Response.ok().build();
    }
    
}
