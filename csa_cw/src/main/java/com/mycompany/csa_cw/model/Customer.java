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

public class Customer {
    
    private int id;// customer id
    private String name;// customer name
    private String email; // customer email
    private String password;// customer password

    //default constructor
    public Customer() {
    }

    // fully argument constructor with JSON annotations
    @JsonCreator
    public Customer(
            @JsonProperty("id")int id,
            @JsonProperty("name")String name,
            @JsonProperty("email")String email,
            @JsonProperty("password")String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
