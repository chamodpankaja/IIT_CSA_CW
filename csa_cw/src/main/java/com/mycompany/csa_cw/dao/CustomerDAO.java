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

    /**
     * retrieves the all customers in the system
     * 
     * @return List of the customers
     */
    public List<Customer> getAllCustomers() {

        return new ArrayList<>(customers);
    }
    
   /**
    * retrieves a customer by their ID
    * 
    * @param customerID id of the customer
    * @return the customer object
    * @throws CustomerNotFoundException if customer not found with given ID
    */
    public Customer getCustomerById(int customerId) {

        return customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElseThrow(()
                        -> new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found")
                );

    }

    /**
     * add new customer to the system
     * 
     * @param customer object of contains the details of new customer
     * @returns newly added Customer object
     * @throws InvalidInputException when input validation are fails
     */
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
    
    /**
     * update an existing customers details
     * 
     * @param customerId id of the customer
     * @param updatedCustomer customer object containing the updated details
     * @return updated customer object
     * @throws CustomerNotFoundException if customer not found with given ID
     * @throws InvalidInputException when input validation are fails
     */
    public Customer updateCustomer(int customerId, Customer updatedCustomer) {

        Customer existingCustomer = getCustomerById(customerId);

        validateCustomer(updatedCustomer);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPassword(updatedCustomer.getPassword());

        return existingCustomer;

    }
    
    /**
     * delete the customer from the system by their id
     * 
     * @param customerId  id of the customer
     * @throws CustomerNotFoundException if customer not found with given ID
     */
    public void deleteCustomer(int customerId) {

        boolean removed = customers.removeIf(customer -> customer.getId() == customerId);

        if (!removed) {
            throw new CustomerNotFoundException("Customer with ID: " + customerId + " not found");
        }

    }
    /**
     * validate the customer input fields
     * 
     * @param customer Customer object to validate fields
     * @throws InvalidInputException when input validation are fails
     */
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

    /**
     * Checks if a customer with the given ID exists in the system.
     *
     * @param customerId the ID to check
     * @return true if customer exists, false otherwise
     */
    public boolean customerExists(int customerId) {
        return customers.stream().anyMatch(c -> c.getId() == customerId);
    }

}
