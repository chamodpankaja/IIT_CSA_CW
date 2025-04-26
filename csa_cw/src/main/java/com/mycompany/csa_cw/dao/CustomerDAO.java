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
    //arrayList for store the customer details
    private static List<Customer> customers = new ArrayList<>();
    //atomic integer for generate the customer id
    private static AtomicInteger id = new AtomicInteger(1);

    static {

        // add customers to the system
        customers.add(new Customer(id.getAndIncrement(), "Chamod Pankaja", "pankaja@gmail.com", "pankaja123"));
        customers.add(new Customer(id.getAndIncrement(), "Alice Johnson", "alice.johnson@example.com", "alicepass"));
        customers.add(new Customer(id.getAndIncrement(), "Bob Smith", "bob.smith@example.com", "bobsecure"));
        customers.add(new Customer(id.getAndIncrement(), "Emily Davis", "emily.davis@example.com", "emilypass123"));
        customers.add(new Customer(id.getAndIncrement(), "Liam Brown", "liam.brown@example.com", "liambrown456"));

    }

    // method for get the all customer details
    public List<Customer> getAllCustomers() {

        return new ArrayList<>(customers);
    }
    
    // method for the get customer details using customer id
    public Customer getCustomerById(int customerId) {

        return customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElseThrow(()
                        -> new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found")
                );

    }

    // method for add customer to the system
    public Customer addCustomer(Customer customer) {

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
    
    // method for the update customer details
    public Customer updateCustomer(int customerId, Customer updatedCustomer) {

        Customer existingCustomer = getCustomerById(customerId);

        validateCustomer(updatedCustomer);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPassword(updatedCustomer.getPassword());

        return existingCustomer;

    }
    
    // method for delete customer
    public void deleteCustomer(int customerId) {

        boolean removed = customers.removeIf(customer -> customer.getId() == customerId);

        if (!removed) {
            throw new CustomerNotFoundException("Customer with ID: " + customerId + " not found");
        }

    }
    // method for the validate cutomer input fields
    private void validateCustomer(Customer customer) throws InvalidInputException {

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {

            throw new InvalidInputException("Customer name cannot be null or empty.");
        }
        if (customer.getEmail() == null
                || !customer.getEmail().matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b")) {

            throw new InvalidInputException("Invalid Email Address.");
        }
        if (customer.getPassword() == null || customer.getPassword().length() < 6) {

            throw new InvalidInputException("Password must be at least 6 characters. ");
        }

    }

    //method for the find the customer is exist on the system
    public boolean customerExists(int customerId) {
        return customers.stream().anyMatch(c -> c.getId() == customerId);
    }

}
