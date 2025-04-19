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
    private int bookId;
    private int quantity;

    // Default constructor (required for Jackson)
    public CartItem() {}

    // Full constructor with JSON annotations
    @JsonCreator
    public CartItem(
            @JsonProperty("bookId") int bookId,
            @JsonProperty("quantity") int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    // Getters and setters (required for Jackson)
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
}