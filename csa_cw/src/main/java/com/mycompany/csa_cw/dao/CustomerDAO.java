/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.CustomerNotFoundException;
import com.mycompany.csa_cw.exceptions.InvalidInputException;
import com.mycompany.csa_cw.model.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author chamodpankaja
 */
public class CustomerDAO {
    
    private static List<Customer> customers =  new ArrayList<>();
    private static AtomicInteger id = new AtomicInteger(1);
    
    
    
    public List<Customer> getAllCustomers(){
    
        return new ArrayList<>(customers);
    }
    
    
    public Customer getCustomerById(int customerId){
    
        return customers.stream()
                .filter(c-> c.getId() == customerId)
                .findFirst()
                .orElseThrow(() ->
                
                  new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found")
                );
        
    }
    
    
    public Customer addCustomer(Customer customer){
    
        validateCustomer(customer);
        
        Customer newCustomer = new Customer(
        
                id.getAndIncrement(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword()

        );
        customers.add(newCustomer);
        return newCustomer;
    
    
    }
    
    
    public Customer updateCustomer(int customerId, Customer updatedCustomer){
    
        Customer existingCustomer = getCustomerById(customerId);
        
        validateCustomer(updatedCustomer);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPassword(updatedCustomer.getPassword());
        
        return existingCustomer;
    
    }
    
    
    
    public void deleteCustomer(int customerId){
    
        boolean removed = customers.removeIf(customer -> customer.getId() == customerId );

        if (!removed) {
            throw new CustomerNotFoundException("Customer with ID: " + customerId + " not found");
        }
    
    }
    
    
    
    private  void validateCustomer( Customer customer) throws InvalidInputException{
        
        if(customer.getName() == null || customer.getName().trim().isEmpty()){
            
            throw new InvalidInputException("Customer name cannot be null or empty.");
        }
        if(customer.getEmail() == null ||
                !customer.getEmail().matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b")){
            
            throw new InvalidInputException("Invalid Email Address.");
        }
        if(customer.getPassword() ==  null || customer.getPassword().length()<6){
        
            throw new InvalidInputException("Password must be at least 6 characters. ");
        }
    
    
    }
    
    
}
