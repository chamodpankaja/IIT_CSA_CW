/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author chamodpankaja
 */
public class Order {
    
    private int id;
    private int customerId;
    private Date orderDate;
    private List<CartItem> items;
    private double total;

    public Order() {
    }

    public Order(int id,int customerId, Date orderDate, List<CartItem> items, double total) {
        this.id = id;
        this.customerId =  customerId;
        this.orderDate = orderDate;
        this.items = items;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    
    
}
