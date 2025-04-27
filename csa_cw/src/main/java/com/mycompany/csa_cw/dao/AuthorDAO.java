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

    /**
     * retrieves the all authors in the system
     * 
     * @return new list of all \authors
     */
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors);

    }
    
   /**
    * retrieves the author by their author id
    * 
    * @param id the Id of the author
    * @return the author object
    * @throws AuthorNotFoundException if author is not found
    */
    public Author getAuthorById(int id) throws AuthorNotFoundException {
        
    return authors.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElseThrow(() -> 
                new AuthorNotFoundException("Author with ID: " + String.valueOf(id) + " not found"));
        
    }
    
    /**
     * method for  the add new author to the system
     * 
     * @param author object of Author
     * @return added author object
     * @throws  InvalidInputException when fields not properly validated
     */
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
    
    /**
     * delete an author by their id
     * 
     * @param authorId of the author to delete
     * @return success message when author is successfully deleted
     * @throws AuthorNotFoundException if no author with specific id exists
     */
    
    public String deleteAuthor(int authorId) {

        boolean removed = authors.removeIf(author -> author.getId() == authorId);

        if (!removed) {
            throw new AuthorNotFoundException("Author with ID: " + authorId + " not found");
        }

        return "Author deleted successfully with ID: " + authorId;

    }
    
    /**
     * method for the update existing author details
     * 
     * @param authorId the id of the author to update
     * @param updatedAuthor the Author object of the updated information
     * @return updated author object
     * @throws AuthorNotFoundException if no author with specific id exists
     * @throws InvalidInputException when fields not properly validated
     */
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
    
    
    /**
     * check if an author exists in the system
     * 
     * @param  authorId the Id of the author to check
     * @return true if author is exists otherwise false
     */
    public boolean authorExists(int authorId) {
        return authors.stream().anyMatch(a -> a.getId() == authorId);
    }

}
