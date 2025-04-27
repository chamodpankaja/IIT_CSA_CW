/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.BookNotFoundException;
import com.mycompany.csa_cw.exceptions.CustomerNotFoundException;
import com.mycompany.csa_cw.exceptions.InvalidInputException;
import com.mycompany.csa_cw.exceptions.OutOfStockException;
import com.mycompany.csa_cw.model.Book;
import com.mycompany.csa_cw.model.Cart;
import com.mycompany.csa_cw.model.CartItem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chamodpankaja
 */
public class CartDAO {

    // hashmap for the store the cart details
    private static Map<Integer, Cart> carts = new HashMap<>();
    // BookDAO instance for manage the book related operations
    private final BookDAO bookDAO = new BookDAO();
    // CustomerDAO instance for manage the customer related operations
    private final CustomerDAO customerDAO = new CustomerDAO();

    
    /**
     * retrieve the cart of specific customer by their customer id
     * 
     * @param customerId
     * @return cart object belongs to customer
     * @throws  CustomerNotFoundException when customer is not found
     */
    public Cart getCart(int customerId) throws CustomerNotFoundException {

        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CustomerNotFoundException("Cart not found for customer ID: " + String.valueOf(customerId));
        }

        return cart;

    }
    
    /**
     * Adds an item to the customer's cart. 
     * If the cart does not exist, creates a new cart for the customer.
     * Also reserves stock for the book.
     * 
     * @param customerID id of the customer
     * @param cartItem cart item to add
     * @param bookDAO instance of the BookDAO
     * @return added cart
     * @throws  CustomerNotFoundException when customer is not found
     * @throws BookNotFoundException when book is not found
     * @throws OutOfStockException if there is not enough stock
     */
    public Cart addItem(int customerId, CartItem cartItem, BookDAO bookDAO) throws BookNotFoundException, OutOfStockException, CustomerNotFoundException {

        // Validate input
        if (!customerDAO.customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }
        if (cartItem == null) {
            throw new  InvalidInputException("Cart item cannot be null");
        }
        if (cartItem.getQuantity() <= 0) {
            throw new InvalidInputException("Quantity must be positive");
        }

        // Reserve stock
        bookDAO.reserveBook(cartItem.getBookId(), cartItem.getQuantity());

        // Get or create cart
        Cart cart = carts.computeIfAbsent(customerId, k -> new Cart(customerId));
    
        
        // get the book details
        Book book = bookDAO.getBookById(cartItem.getBookId());
          // Add item to cart
        cart.getItems().add(new CartItem(
                book.getBookId(),
                book.getBookTitle(),
                cartItem.getQuantity(),
                book.getPrice()
        ));
        return cart;

    }

    /**
     * Updates the quantity of a specific item in the customer's cart.
     * Adjusts the book stock accordingly.
     *
     * @param customerID id of the customer
     * @param bookId id of the book
     * @param newQuantity new quantity to the update
     * @return updated cart
     * @throws CustomerNotFoundException when customer is not found
     * @throws BookNotFoundException when book is not found
     * @throws OutOfStockException if there is not enough stock
     */
    public Cart updateItem(int customerId, int bookId, int newQuantity) throws CustomerNotFoundException, BookNotFoundException, OutOfStockException {

        if (!customerDAO.customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }

        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CustomerNotFoundException("Cart not found for customer ID: " + String.valueOf(customerId));
        }

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getBookId() == bookId)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID: " + bookId + " not found in cart"));
        
        // get the old quantity and calculate the quantity difference of the cart item
        int oldQuantity = existingItem.getQuantity();
        int quantityDifference = newQuantity - oldQuantity;
        
        //if difference > 0 decrease the original stock quantity
        if (quantityDifference > 0) {

            bookDAO.reserveBook(bookId, quantityDifference);
        } else if (quantityDifference < 0) {
            
             //if difference < 0 increase the original stock quantity
            bookDAO.restockBook(bookId, -quantityDifference);
        }

        existingItem.setQuantity(newQuantity);

        return cart;

    }

    /**
     * deletes the item from customers cart
     * restock the item based on remove item quantity
     * 
     * @param customerId id of the customer
     * @param bookId id of the book
     * @return cart
     * @throws CustomerNotFoundException when customer is not found
     * @throws BookNotFoundException when book is not found
     */
    public Cart deletItem(int customerId, int bookId) throws CustomerNotFoundException, BookNotFoundException {

        if (!customerDAO.customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID: " + String.valueOf(customerId) + " not found");
        }

        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CustomerNotFoundException("Cart not found for customer ID: " + String.valueOf(customerId));
        }

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getBookId() == bookId)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with ID: " + bookId + " not found in cart"));

        bookDAO.restockBook(bookId, itemToRemove.getQuantity());
        cart.getItems().removeIf(item -> item.getBookId() == bookId);

        return cart;

    }
}
