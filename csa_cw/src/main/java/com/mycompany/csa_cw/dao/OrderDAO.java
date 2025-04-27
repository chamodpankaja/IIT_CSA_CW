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
    
    // hash map for the store order details
    private static Map<Integer , List<Order>> customerOrders = new HashMap<>();
    // atomic integer for the generate order id
    private static AtomicInteger orderId = new AtomicInteger(1);
    // Instance of CartDAO to manage customer carts
    private final CartDAO cartDAO =  new CartDAO();
    //  Instance of BookDAO to retrieve book information
    private final BookDAO bookDAO = new BookDAO();
    // Instance of CustomerDAO to manage customer data
    private final CustomerDAO customerDAO = new CustomerDAO();
    
    /**
     * create a new order for customer
     * 
     * @param customerId id of the customer
     * @return created order object
     * @throws CustomerNotFoundException if customer not found with given ID
     * @throws CartNotFoundException if the cart is empty or not found
     * 
     */
    public Order createOrder(int customerId) throws CustomerNotFoundException,CartNotFoundException{
        
        // check the customer is available on the system or not
        if(!customerDAO.customerExists(customerId)){
        
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        
        Cart cart = cartDAO.getCart(customerId);
        if(cart == null || cart.getItems().isEmpty()){
        
            throw new CartNotFoundException("cannot create order from empty cart");
        }
        
        // calculate the total amount of the order
        double total = 0;
        for(CartItem item : cart.getItems()){
        
            total+=bookDAO.getBookById(item.getBookId()).getPrice()*item.getQuantity();
        }
        
        Order order =  new Order(orderId.getAndIncrement(),customerId,customerDAO.getCustomerById(customerId).getName(),customerDAO.getCustomerById(customerId).getEmail(),new Date(),new ArrayList<>(cart.getItems()),total);
        
        customerOrders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(order);
        
        // after the order place clear the cart details
        cart.getItems().clear();
        return order;
        
    }
   
    
    /**
     * retrieves the all orders of the customer
     * 
     * @param customerId id of the customer
     * @return list of the order objects of customer
     * @throws CustomerNotFoundException if customer not found with given ID
     */
    public List<Order> getCustomerOrders(int customerId) throws CustomerNotFoundException{
    
        if(!customerDAO.customerExists(customerId)){
        
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    
    }
    
    /**
     * retrieves the specific order by its order ID
     * 
     * @param customerId id of the customer
     * @param orderId id of the order
     * @return order object
     * @throws CustomerNotFoundException if customer not found with given ID
     */
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
