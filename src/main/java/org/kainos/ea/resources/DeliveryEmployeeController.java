package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.DeliveryEmployeeService;
import org.kainos.ea.cli.DeliveryEmployeeRequest;
import org.kainos.ea.client.FailedToCreateEmployeeException;
import org.kainos.ea.client.InvalidEmployeeException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Delivery Employees")
@Path("/api")
public class DeliveryEmployeeController {

    private DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService();

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

}
