package org.kainos.ea.resources;


import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.api.SalesEmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesRequest;
import org.kainos.ea.client.FailedToCreateEmployeeException;
import org.kainos.ea.client.FailedToGetSalesException;
import org.kainos.ea.client.InvalidEmployeeException;
import org.kainos.ea.client.SaleDoesNotExistException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Sales Employees")
@Path("/api")
public class SalesController {

    private SalesEmployeeService salesEmployeeService = new SalesEmployeeService();


    @GET
    @Path("/sales-employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSales()
    {
        try {
            return Response.ok(salesEmployeeService.getAllSales()).build();
        } catch (FailedToGetSalesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/sales-employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesById(@PathParam("id") int id)
    {
        try {
            try {
                return Response.ok(salesEmployeeService.getSalesById(id)).build();
            } catch (SaleDoesNotExistException e) {
                throw new RuntimeException(e);
            }
        } catch (FailedToGetSalesException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @POST
    @Path("/sales-employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSales(SalesRequest sale) {
        try{
            return Response.status(Response.Status.CREATED).entity(salesEmployeeService.createSalesEmployee(sale)).build();
        } catch (FailedToCreateEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidEmployeeException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}