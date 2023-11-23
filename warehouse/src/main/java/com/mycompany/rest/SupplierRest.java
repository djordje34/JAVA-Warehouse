/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest;
import com.mycompany.data.Supplier;
import com.mycompany.exception.WarehouseException;
import com.mycompany.service.SupplierService;
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
@Path("suppliers")
public class SupplierRest {
    private final SupplierService supplierService = SupplierService.getInstance();
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List <Supplier> getAllSuppliers() throws WarehouseException{
        return supplierService.findAllSuppliers();
    }
    
    @GET
    @Path("/{SupplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getSupplierById(@PathParam("SupplierId") int supplierId) throws WarehouseException{
        return supplierService.findSupplier(supplierId);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSupplier(Supplier supplier) throws WarehouseException{
            supplierService.addNewSupplier(supplier);
            return Response.ok().build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplier(Supplier supplier) throws WarehouseException {
            supplierService.updateSupplier(supplier);
            return Response.ok().build();
    }
    
    
    @DELETE
    @Path("/{SupplierId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@PathParam("SupplierId") int supplierId) throws WarehouseException{
            supplierService.deleteSupplier(supplierId);
            return Response.ok().build();
    }
    
}
