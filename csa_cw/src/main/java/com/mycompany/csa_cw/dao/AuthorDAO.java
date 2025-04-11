/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.AuthorNotFoundException;
import com.mycompany.csa_cw.model.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author chamodpankaja
 */
public class AuthorDAO {
    
    private static List<Author> authors = new ArrayList<>();
    private static AtomicInteger id =  new AtomicInteger(1);
    
    static{
    
        authors.add(new Author(id.getAndIncrement(), 
            "J.R.R. Tolkien", 
            "Author of The Lord of the Rings"));
    
    }
    
    public List<Author> getAllAuthors(){
        return new ArrayList<>(authors);
    
    }
    
    public Author getAuthorById(int id) {
    return authors.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElseThrow(() -> 
                new AuthorNotFoundException("Author with ID: " + String.valueOf(id) + " not found"));

    }
    
    public Author addAuthor(Author author){
    
        author.setId(id.getAndIncrement());
        authors.add(author);
        return author;
    
    }
    
    
    
}
