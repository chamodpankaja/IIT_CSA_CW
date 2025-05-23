/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author chamodpankaja
 */
public class CartItem {
    
    private int bookId;// book ID
    private String bookName; // book name
    private int quantity; // quantity of the item
    private double price; // price 
    

    // Default constructor
    public CartItem() {
    
    }

    // fully argument constructor with JSON annotations
    @JsonCreator
    public CartItem(
            @JsonProperty("bookId") int bookId,
            @JsonProperty("bookName") String bookName,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("price") double price) {
        this.bookId = bookId;
        this.bookName =  bookName;
        this.quantity = quantity;
        this.price =  price;
    }

    // Getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}