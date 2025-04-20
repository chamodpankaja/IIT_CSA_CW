/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.resource;

import com.mycompany.csa_cw.dao.OrderDAO;
import com.mycompany.csa_cw.model.Order;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author chamodpankaja
 */

@Path("/customers/{customerId}/orders")
public class OrderResource {
    
    
    private OrderDAO orderDAO  = new OrderDAO();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(@PathParam("customerId") int customerId){
    
        Order newOrder =  orderDAO.createOrder(customerId);
        return Response.status(Response.Status.CREATED)
                    .entity(newOrder)
                    .build();
    
    
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerOrders(@PathParam("customerId") int customerId){
    
        return Response.ok(orderDAO.getCustomerOrders(customerId)).build();
    
    }
    
    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("customerId") int customerId , @PathParam("orderId") int orderId){
    
        Order order = orderDAO.getOrderById(customerId, orderId);
            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\":\"Order not found\"}")
                        .build();
            }
            return Response.ok(order).build();
    }
    
    
}
