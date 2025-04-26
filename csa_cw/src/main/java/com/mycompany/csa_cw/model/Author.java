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
public class Author {
    
    private int id; //author ID
    private String name; // Author name
    private String biography; // Author's biography

    // default constructor
    public Author() {
    }
    
     // fully argument constructor with JSON annotations
    @JsonCreator
    public Author(
            @JsonProperty("id")int id, 
            @JsonProperty("name")String name,
            @JsonProperty("biography")String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    
}
