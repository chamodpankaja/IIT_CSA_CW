/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.AuthorNotFoundException;
import com.mycompany.csa_cw.exceptions.BookNotFoundException;
import com.mycompany.csa_cw.exceptions.InvalidInputException;
import com.mycompany.csa_cw.model.Book;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author chamodpankaja
 */
public class BookDAO {
    
    private static List<Book> books = new ArrayList<>();
    private static AtomicInteger id = new AtomicInteger(100100);
    private final AuthorDAO authorDAO = new AuthorDAO();
    
    static{

        books.add(new Book(id.getAndIncrement(), "The Lord of the Rings", 1, 
                          "978-0-618-05326-7", 1954, 20.99, 100));
         books.add(new Book(id.getAndIncrement(), "The Lord of the Rings", 1, 
                          "978-0-618-05326-7", 1954, 20.99, 100));
    }
    
    public List<Book> getAllBooks(){
    
        return new ArrayList<>(books);
    }
    
    public Book getBookById(int id) {
    return books.stream()
            .filter(b -> b.getBookId() == id)
            .findFirst()
            .orElseThrow(() -> 
                new BookNotFoundException("Book with ID: " + String.valueOf(id) + " not found"));

    }
    
    
    public Book addBook(Book book){
    
       
        try{
            
             validateBook(book);
            Book newBook =  new Book(
        
                id.getAndIncrement(),
                book.getBookTitle(),
                book.getAuthorId(),
                book.getISBN(),
                book.getPublicationYear(),
                book.getPrice(),
                book.getStockQuantity()
                
                
        );
        
        books.add(newBook);
        return newBook;
        
        
        
        }catch(InputMismatchException e ){
        
            throw new InvalidInputException("Invalid input type provided: " + e.getMessage());
        }
        
    }
    
    private void validateBook(Book book) throws InvalidInputException , AuthorNotFoundException{
        validateAuthorExists(book.getAuthorId());
        validatePublicationYear(book.getPublicationYear());
    }
    
    private void validateAuthorExists(int authorId) throws AuthorNotFoundException{
        if(authorDAO.getAuthorById(authorId) == null){
            throw new AuthorNotFoundException("Author with ID : " +String.valueOf(authorId) + " not found.");
        }
    }
    
    private void validatePublicationYear(int year) throws InvalidInputException{
    
        if(year > java.time.Year.now().getValue()){
            throw new InvalidInputException("Publication year cannot be in the future.");
        }else if(year<0){
        
             throw new InvalidInputException("Publication year cannot be a negetive value.");
        }
    }

    
}
