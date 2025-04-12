/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.AuthorNotFoundException;
import com.mycompany.csa_cw.exceptions.InvalidInputException;
import com.mycompany.csa_cw.model.Author;
import com.mycompany.csa_cw.model.Book;
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
    private static List<Book> books = new ArrayList<>();
    
    static{
    
        authors.add(new Author(id.getAndIncrement(), 
            "J.R.R. Tolkien", 
            "Author of The Lord of the Rings"));
        authors.add(new Author(id.getAndIncrement(), 
            "chamod pankaja", 
            "Author of The harry potter"));
    
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
        
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be null or empty.");
        }

        if (author.getBiography() == null || author.getBiography().trim().isEmpty()) {
            throw new InvalidInputException("Author biography cannot be null or empty.");
        }
    
        author.setId(id.getAndIncrement());
        authors.add(author);
        return author;
    
    }
    
    public String deleteAuthor(int authorId){
        
        boolean removed = authors.removeIf(author -> author.getId() == authorId);
        
        if (!removed) {
            throw new AuthorNotFoundException("Author with ID: " + authorId + " not found");
        }

        return "Author deleted successfully with ID: " + authorId;
        
    }
    
    public Author updateAuthor(int authorId, Author updatedAuthor){
    
        Author existingAuthor = getAuthorById(authorId);
        
        if (updatedAuthor.getName() == null || updatedAuthor.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be null or empty.");
        }

        if (updatedAuthor.getBiography() == null || updatedAuthor.getBiography().trim().isEmpty()) {
            throw new InvalidInputException("Author biography cannot be null or empty.");
        }
        
        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setBiography(updatedAuthor.getBiography());
        
        return existingAuthor;
        
    
    }
    
//    public List<Book> getBookByAuthor(int authorId){
//    
//    
//        List<Book> authorWroteBooks = new ArrayList<>();
//        
//        
//        for(Book book : books){
//        
//            if(book.getAuthorId() ==authorId){
//            
//                authorWroteBooks.add(book);
//            }
//        
//        }
//        
//     
//
//        return authorWroteBooks;
//    
//    }
    
    
    public boolean authorExists(int authorId) {
        return authors.stream().anyMatch(a -> a.getId() == authorId);
    }
    
    
    
}
