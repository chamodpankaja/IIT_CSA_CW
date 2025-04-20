/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.BookNotFoundException;
import com.mycompany.csa_cw.exceptions.CustomerNotFoundException;
import com.mycompany.csa_cw.exceptions.OutOfStockException;
import com.mycompany.csa_cw.model.Cart;
import com.mycompany.csa_cw.model.CartItem;
import java.util.HashMap;
import java.util.Map;

public class CartDAO {
    
    private static Map<Integer, Cart> carts = new HashMap<>();
    private final BookDAO bookDAO = new BookDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    
    public Cart getCart(int customerId) throws CustomerNotFoundException {
        
        Cart cart =  carts.get(customerId);
        if(cart ==  null){
             throw new CustomerNotFoundException("Cart not found for customer ID: " + String.valueOf(customerId) );
        }
        
        return cart;
        
    }
    
    public Cart addItem(int customerId, CartItem cartItem) throws BookNotFoundException, OutOfStockException,CustomerNotFoundException {
        
            // Validate input
         
            if (!customerDAO.customerExists(customerId)) {
                throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
            }
            if (cartItem == null) {
                throw new IllegalArgumentException("Cart item cannot be null");
            }
            if (cartItem.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            
            // Reserve stock
            bookDAO.reserveBook(cartItem.getBookId(), cartItem.getQuantity());
            
            // Get or create cart
            Cart cart = carts.computeIfAbsent(customerId, k -> new Cart(customerId));
            
            // Add item to cart
            cart.getItems().add(new CartItem(cartItem.getBookId(), cartItem.getQuantity()));
            
            return cart;
       
    }
    
    public Cart updateItem(int customerId, int bookId, int newQuantity) throws CustomerNotFoundException,BookNotFoundException,OutOfStockException{
    
        if (!customerDAO.customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        
        Cart cart =  carts.get(customerId);
        if(cart ==  null){
             throw new CustomerNotFoundException("Cart not found for customer ID: " + String.valueOf(customerId) );
        }
        
        CartItem existingItem =  cart.getItems().stream()
                .filter(item -> item.getBookId() == bookId)
                .findFirst()
                .orElseThrow(() ->new BookNotFoundException("Book with ID: " + bookId + " not found in cart") );
        
        
        int oldQuantity =  existingItem.getQuantity();
        int quantityDifference = newQuantity - oldQuantity;
        
        if(quantityDifference > 0 ){
        
            bookDAO.reserveBook(bookId, quantityDifference);
        }else if(quantityDifference < 0){
        
            bookDAO.restockBook(bookId, -quantityDifference);
        }
        
        existingItem.setQuantity(newQuantity);
        
        return cart;
    
    }
    
    public Cart deletItem(int customerId, int bookId) throws CustomerNotFoundException, BookNotFoundException{
    
         if (!customerDAO.customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        
        Cart cart =  carts.get(customerId);
        if(cart ==  null){
             throw new CustomerNotFoundException("Cart not found for customer ID: " + String.valueOf(customerId) );
        }
        
        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getBookId() == bookId)
                .findFirst()
               .orElseThrow(() ->new BookNotFoundException("Book with ID: " + bookId + " not found in cart") );
        
        
        bookDAO.restockBook(bookId, itemToRemove.getQuantity());
        cart.getItems().removeIf(item -> item.getBookId() == bookId);
        
        return cart;

    }
}