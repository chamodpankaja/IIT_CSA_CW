/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.resource;

import com.mycompany.csa_cw.dao.CustomerDAO;
import com.mycompany.csa_cw.model.Customer;
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
 * @author chamodpankaja
 */

@Path("/customers")
public class CustomerResorce {
    
    
    private CustomerDAO customerDAO = new CustomerDAO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers(){
    
        return customerDAO.getAllCustomers();
    }
    
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer geCustomerById(@PathParam("customerId") int cusomerId){
        
        return customerDAO.getCustomerById(cusomerId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer){
    
        Customer addCustomer =  customerDAO.addCustomer(customer);
        return Response
                .status(Response.Status.CREATED)
                .entity(addCustomer)
                .build();
    }
    
    @DELETE
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("customerId") int customerId) {
        customerDAO.deleteCustomer(customerId);
        return Response.noContent().build(); 
    }
    
    
    @PUT
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer updateCustomer (@PathParam("customerId") int customerId , Customer customer){
    
        return customerDAO.updateCustomer(customerId, customer);
                
    }
    
    
    
}
