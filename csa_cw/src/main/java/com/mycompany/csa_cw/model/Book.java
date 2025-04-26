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
public class Book {
    
    private int bookId;// book Id
    private String bookTitle;// book title
    private int authorId; // author Id
    private String ISBN; // isbn number
    private int publicationYear; // publiction year
    private double price; // price of the book
    private int stockQuantity; // stock 
    
    // default constructor
    public Book(){
    
    }
    // fully argument constructor with JSON annotations
    @JsonCreator
    public Book(
            @JsonProperty("bookId")int bookId, 
            @JsonProperty("bookTitle")String bookTitle, 
            @JsonProperty("authorId")int authorId,
            @JsonProperty("ISBN")String ISBN, 
            @JsonProperty("publicationYear")int publicationYear, 
            @JsonProperty("price")double price,
            @JsonProperty("stockQuantity")int stockQuantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.authorId = authorId;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    
    
    // getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    
    
    
                
    
    
}
