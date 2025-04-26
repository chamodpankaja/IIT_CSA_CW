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

    // ArrayList for store the author details
    private static List<Author> authors = new ArrayList<>();
    // atomic integer is used to generate the author id
    private static AtomicInteger id = new AtomicInteger(1);
    //private static List<Book> books = new ArrayList<>();

    static {

        // Add books to the system
        authors.add(new Author(id.getAndIncrement(),
                "Robert C. Martin",
                "Author of Clean Code and other programming best practices"));
        authors.add(new Author(id.getAndIncrement(),
                "Joshua Bloch",
                "Author of Effective Java and Java programming expert"));
        authors.add(new Author(id.getAndIncrement(),
                "Guido van Rossum",
                "Creator of the Python programming language"));
        authors.add(new Author(id.getAndIncrement(),
                "Brian W. Kernighan",
                "Co-author of The C Programming Language"));
        authors.add(new Author(id.getAndIncrement(),
                "Bjarne Stroustrup",
                "Creator of C++ and author of The C++ Programming Language"));
        authors.add(new Author(id.getAndIncrement(),
                "Martin Fowler",
                "Expert on software architecture and author of Refactoring"));
        authors.add(new Author(id.getAndIncrement(),
                "Ken Thompson",
                "Pioneer in UNIX and C programming"));

    }

    // method for gett all authors
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors);

    }
    
    // method for get author details with their id
    public Author getAuthorById(int id) throws AuthorNotFoundException {
        
    return authors.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElseThrow(() -> 
                new AuthorNotFoundException("Author with ID: " + String.valueOf(id) + " not found"));
        
    }
    
    // method for add author to system
    public Author addAuthor(Author author) {
        
        // check the input fields are correct or not
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
    
    // method for delete author
    public String deleteAuthor(int authorId) {

        boolean removed = authors.removeIf(author -> author.getId() == authorId);

        if (!removed) {
            throw new AuthorNotFoundException("Author with ID: " + authorId + " not found");
        }

        return "Author deleted successfully with ID: " + authorId;

    }
    
    //method for update wuthor details
    public Author updateAuthor(int authorId, Author updatedAuthor) {

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
    
    //method to check author is exist in the system
    
    public boolean authorExists(int authorId) {
        return authors.stream().anyMatch(a -> a.getId() == authorId);
    }

}
