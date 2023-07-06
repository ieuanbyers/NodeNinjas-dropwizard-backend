package org.kainos.ea.resources;


import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.api.SalesEmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.SalesRequest;
import org.kainos.ea.client.FailedToCreateEmployeeException;
import org.kainos.ea.client.InvalidEmployeeException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Sales Employees")
@Path("/api")
public class SalesController {

    private SalesEmployeeService salesEmployeeService = new SalesEmployeeService();

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