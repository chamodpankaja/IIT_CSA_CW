/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chamodpankaja
 */
public class Order {
    
    private int orderId;
    private int customerId;
    private String customerName;
    private String customerMail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Colombo")
    private Date orderDate;
    private List<CartItem> items;
    private double total;

    public Order() {
    }

    // fully argument constructor with JSON annotations
    @JsonCreator
    public Order(
            @JsonProperty("orderId")int orderId,
            @JsonProperty("customerId")int customerId,
            @JsonProperty("customerName")String customerName,
            @JsonProperty("customerMail")String customerMail, 
            @JsonProperty("Date")Date orderDate, 
            @JsonProperty("items")List<CartItem> items,
            @JsonProperty("total")double total) {
        this.orderId = orderId;
        this.customerId =  customerId;
        this.customerName =  customerName;
        this.customerMail = customerMail;
        this.orderDate = orderDate;
        this.items = items;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }
    
    
}
