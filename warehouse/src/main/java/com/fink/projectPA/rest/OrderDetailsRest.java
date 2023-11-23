/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.rest;

/**
 *
 * @author Djordje
 */

import com.fink.projectPA.data.OrderDetails;
import com.fink.projectPA.exception.WarehouseException;
import com.fink.projectPA.service.OrderDetailsService;
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

@Path("orderdetails")
public class OrderDetailsRest {
    private final OrderDetailsService orderDetailsService = OrderDetailsService.getInstance();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List <OrderDetails> getAllOrderDetails() throws WarehouseException{
        return orderDetailsService.findAllOrderDetails();
    }
    
    @GET
    @Path("/{OrderDetailsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetails getOrderDetailsById(@PathParam("OrderDetailsId") int orderDetailsId) throws WarehouseException{
        return orderDetailsService.findOrderDetails(orderDetailsId);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderDetails(OrderDetails orderDetails) throws WarehouseException{
            orderDetailsService.addNewOrderDetails(orderDetails);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrderDetails(OrderDetails orderDetails) throws WarehouseException {
            orderDetailsService.updateOrderDetails(orderDetails);
            return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/{OrderDetailsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderDetails(@PathParam("OrderDetailsId") int orderDetailsId) throws WarehouseException{
            orderDetailsService.deleteOrderDetails(orderDetailsId);
            return Response.ok().build();
    }
}
