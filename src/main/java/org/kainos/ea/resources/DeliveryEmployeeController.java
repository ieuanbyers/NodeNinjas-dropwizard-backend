package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.cli.EmployeeRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Delivery Employees")
@Path("/api")
public class DeliveryEmployeeController {

    private DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();

    @GET
    @Path("/delivery-employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeeByID(@PathParam("id") int id){

        try {
            return Response.ok(deliveryEmployeeService.getDeliveryEmployeeByID(id)).build();
        } catch (FailedToGetEmployeeException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        } catch (EmployeeDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();        }
    }

    @POST
    @Path("/delivery-employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(DeliveryEmployeeRequest employee) {
        try{
            return Response.status(Response.Status.CREATED).entity(deliveryEmployeeService.createDeliveryEmployee(employee)).build();
        } catch (FailedToCreateEmployeeException e) {
            System.err.println(e.getMessage());

            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidEmployeeException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/delivery-employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateDeliveryEmployees(@PathParam("id") int id, DeliveryEmployeeRequest employee){

        try{
            deliveryEmployeeService.updateDeliveryEmployee(id, employee);

            return Response.ok().build();
        } catch (FailedToUpdateEmployeeException e){
            System.err.println(e.getMessage());

            return Response.serverError().entity(e.getMessage()).build();
        } catch (InvalidEmployeeException | EmployeeDoesNotExistException e){
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


}
