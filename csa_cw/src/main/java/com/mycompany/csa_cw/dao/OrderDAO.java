/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.CartNotFoundException;
import com.mycompany.csa_cw.exceptions.CustomerNotFoundException;
import com.mycompany.csa_cw.model.Cart;
import com.mycompany.csa_cw.model.CartItem;
import com.mycompany.csa_cw.model.Order;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author chamodpankaja
 */
public class OrderDAO {
    
    private static Map<Integer , List<Order>> customerOrders = new HashMap<>();
    private static AtomicInteger orderId = new AtomicInteger(1);
    private final CartDAO cartDAO =  new CartDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    
    
    public Order createOrder(int customerId) throws CustomerNotFoundException,CartNotFoundException{
        
        if(!customerDAO.customerExists(customerId)){
        
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        
        Cart cart = cartDAO.getCart(customerId);
        if(cart == null || cart.getItems().isEmpty()){
        
            throw new CartNotFoundException("cannot create order from empty cart");
        }
        
        
        double total = 0;
        for(CartItem item : cart.getItems()){
        
            total+=bookDAO.getBookById(item.getBookId()).getPrice()*item.getQuantity();
        }
        
        Order order =  new Order(orderId.getAndIncrement(),customerId,customerDAO.getCustomerById(customerId).getName(),customerDAO.getCustomerById(customerId).getEmail(),new Date(),new ArrayList<>(cart.getItems()),total);
        
        customerOrders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(order);
        
        cart.getItems().clear();
        return order;
        
    }
    
    
    public List<Order> getCustomerOrders(int customerId) throws CustomerNotFoundException{
    
        if(!customerDAO.customerExists(customerId)){
        
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    
    }
    
    public Order getOrderById(int customerId, int orderId ) throws CustomerNotFoundException{
    
        
        if(!customerDAO.customerExists(customerId)){
        
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        return customerOrders.getOrDefault(customerId, new ArrayList<>()).stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

    
    
    
    }
            
}
