/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chamodpankaja
 */
public class Cart {
    
    private int customerId;// customer id
    private List<CartItem> items = new ArrayList<>();//customer ordered items
    
    // fully argument constructor with JSON annotations
    @JsonCreator
    public Cart(
            @JsonProperty("customerId")int customerId) {
        this.customerId = customerId;
    }

    // getteres and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    
    
}
