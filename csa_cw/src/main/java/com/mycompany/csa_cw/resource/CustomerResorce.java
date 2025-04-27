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
    
    // CustomerDAO instance to manage customer related operations
    private CustomerDAO customerDAO = new CustomerDAO();
    
    
    /**
    * retrieves the list of all customers
    * 
    * @return list of all customers
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers(){
    
        return customerDAO.getAllCustomers();
    }
    
    
    /**
     * retrieve specific customer by their id
     * 
     * @param customerId id of the customer
     * @return customer details
     */
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer geCustomerById(@PathParam("customerId") int cusomerId){
        
        return customerDAO.getCustomerById(cusomerId);
    }
    
    /**
     * add new customer
     * 
     * @param, customer object of containing new customer details
     * @return HTTP response of the newly added customer
     */
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
    
    /**
     * delete a customer by their id
     * 
     * @param customerId id of the customer
     * @return HTTP 204 no content response with successful deletion
     */
    @DELETE
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("customerId") int customerId) {
        customerDAO.deleteCustomer(customerId);
        return Response.noContent().build(); 
    }
    
    /**
     * update the details of an existing customer
     * 
     * @param customerId id of the customer
     * @param customer Customer object with updated customer details
     * @return updated customer details
     */
    @PUT
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer updateCustomer (@PathParam("customerId") int customerId , Customer customer){
    
        return customerDAO.updateCustomer(customerId, customer);
                
    }
    
    
    
}
