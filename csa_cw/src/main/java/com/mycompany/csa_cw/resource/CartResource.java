/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.resource;

import com.mycompany.csa_cw.dao.BookDAO;
import com.mycompany.csa_cw.dao.CartDAO;
import com.mycompany.csa_cw.exceptions.BookNotFoundException;
import com.mycompany.csa_cw.exceptions.CustomerNotFoundException;
import com.mycompany.csa_cw.exceptions.OutOfStockException;
import com.mycompany.csa_cw.model.Cart;
import com.mycompany.csa_cw.model.CartItem;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
public class CartResource {
    
    // CartDAO instance to manage cart related operations
    private final CartDAO cartDAO = new CartDAO();
    // BookDAO instance to manage book related operations
    private final BookDAO bookDAO =  new BookDAO();
    
    
    
    /**
     * retrieves cart for a given customer
     * 
     * @param customerId id of the customer
     * @return HTTP response of the cart of the customer
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart(@PathParam("customerId") int customerId){
    
        Cart cart = cartDAO.getCart(customerId);
        return Response.ok(cart).build();
        
    }
    
    /**
     * add an item to customer's cart
     * 
     * @param customerId id of the customer
     * @param item the item to add to the cart
     * @return HTTP response of the added item to the cart
     */
    @POST
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItemToCart(
            @PathParam("customerId") int customerId,
            CartItem item) {
        
            if (item == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Cart item cannot be null\"}")
                        .build();
            }
            
            Cart updatedCart = cartDAO.addItem(customerId, item,bookDAO);
            return Response.status(Response.Status.CREATED)
                    .entity(updatedCart)
                    .build();
        
    }
    
    /**
     * update the quantity of specific cart item
     * 
     * @param customerId id of the customer
     * @param bookId id of the book
     * @param quantity new quantity for the item
     * @return HTTP response of updated cart
     */
    @PUT
    @Path("/items/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("customerId") int customerId ,
            @PathParam("bookId") int bookId, @QueryParam("quantity")int quantity){
        
        Cart updateCart = cartDAO.updateItem(customerId, bookId, quantity);
        return Response.ok(updateCart).build();
        
    
    }
    /**
     * delete the item from cart
     * 
     * @param customerId id of the customer
     * @param bookId id of the book
     * @return HTTP 204 no content response with successful deletion
     */
    @DELETE
    @Path("/items/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("customerId") int customerId ,  @PathParam("bookId") int bookId){
    
        Cart deleteItem =  cartDAO.deletItem(customerId, bookId);
        return Response.noContent().build();
    
    }
    
        
    
    
}