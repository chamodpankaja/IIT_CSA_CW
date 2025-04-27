/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.dao;

import com.mycompany.csa_cw.exceptions.AuthorNotFoundException;
import com.mycompany.csa_cw.exceptions.BookNotFoundException;
import com.mycompany.csa_cw.exceptions.InvalidInputException;
import com.mycompany.csa_cw.exceptions.OutOfStockException;
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

   // array list for store the book details
    private static List<Book> books = new ArrayList<>();
    // atomic integer to generate book id
    private static AtomicInteger id = new AtomicInteger(100100);
    // Instance of AuthorDAO for access the methods belongs to AuthorDAO
    private final AuthorDAO authorDAO = new AuthorDAO();

    static {
        
        // add bokks to the system
        books.add(new Book(id.getAndIncrement(), "Clean Code", 1,
                "978-0-13-235088-4", 2008, 34.99, 50));
        books.add(new Book(id.getAndIncrement(), "Effective Java", 2,
                "978-0-13-468599-1", 2018, 45.00, 40));
        books.add(new Book(id.getAndIncrement(), "Python Programming: An Introduction to Computer Science", 3,
                "978-1-59028-275-5", 2010, 30.00, 60));
        books.add(new Book(id.getAndIncrement(), "The C Programming Language", 4,
                "978-0-13-110362-7", 1978, 25.99, 70));
        books.add(new Book(id.getAndIncrement(), "The C++ Programming Language", 5,
                "978-0-321-56384-2", 2013, 49.99, 30));
        books.add(new Book(id.getAndIncrement(), "Refactoring: Improving the Design of Existing Code", 6,
                "978-0-201-48567-7", 1999, 42.50, 35));
        books.add(new Book(id.getAndIncrement(), "Unix Programming Environment", 7,
                "978-0-13-937681-8", 1984, 29.99, 45));

    }
    
    /**
     * retrieves all books in the system
     * 
     * @return list of all books
     */
    public List<Book> getAllBooks() {

        return new ArrayList<>(books);
    }
 
    
    /**
     * retrieves a book by book id
     * 
     * @param id the id of the book 
     * @return book object if book is found
     * @throws BookNotFoundException if the book is not found
     */
    public Book getBookById(int id) {
        
        return books.stream()
                .filter(b -> b.getBookId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    return new BookNotFoundException("Book with ID: " + id + " not found");
                });
    }

    /**
     * add new book to the system
     * 
     * @param book book object to add
     * @return new added book
     * @throws InvalidInputException if validations are failed
     * @throws AuthorNotFoundException when tries to add book with wrong authorId
     *
     */
    public Book addBook(Book book) {

        try {
            // validate book and input fields
            validateBookInput(book);
            validateBook(book);

            Book newBook = new Book(
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

        } catch (InputMismatchException e) {

            throw new InvalidInputException("Invalid input type provided: " + e.getMessage());
        }

    }

   /**
    * delete the book by bookId
    * 
    * @param id the id of the book 
    * @throws BookNotFoundException if the book is not found in system
    */
    public void deleteBook(int id) {
        boolean removed = books.removeIf(book -> book.getBookId() == id);

        if (!removed) {
            throw new BookNotFoundException("Book with ID: " + id + " not found");
        }

    }
    
    /**
     * update the existing book in the system
     * 
     * @param bookId id of the book
     * @param updatedBook Book object with updated book information
     * @return updated Book object
     * @throws InvalidInputException if validations are failed
     */
    public Book updateBook(int bookId, Book updatedBook) {
        
        // get the exact bok
        Book existingBook = getBookById(bookId);

        try {
            // validate the fileds
            validateBookInput(updatedBook);
            validateBook(updatedBook);

            existingBook.setBookTitle(updatedBook.getBookTitle());
            existingBook.setAuthorId(updatedBook.getAuthorId());
            existingBook.setISBN(updatedBook.getISBN());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setStockQuantity(updatedBook.getStockQuantity());

            return existingBook;

        } catch (InputMismatchException e) {

            throw new InvalidInputException("Invalid input provided : " + e.getMessage());
        }
    }

    /**
     * validates the fields of the book
     * 
     * @param book the book to validate
     * @throws InvalidInputException if any field is invalid
     */
    private void validateBookInput(Book book) throws InvalidInputException {
        if (book.getBookTitle() == null || book.getBookTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty");
        }
        if (book.getISBN() == null || !book.getISBN().matches("\\d{3}-\\d-\\d{3}-\\d{5}-\\d")) {
            throw new InvalidInputException("Invalid ISBN format");
        }
        if (book.getPrice() <= 0) {
            throw new InvalidInputException("Price must be positive");
        }
        if (book.getStockQuantity() < 0) {
            throw new InvalidInputException("Stock quantity cannot be negative");
        }
    }
    
    /**
     * additional validations for the book
     * 
     * @param book the book to validate
     * @throws InvalidInputException if publication year is invalid
     * @throws AuthorNotFoundException if author does not exist
     */
    private void validateBook(Book book) throws InvalidInputException, AuthorNotFoundException {
        validateAuthorExists(book.getAuthorId());
        validatePublicationYear(book.getPublicationYear());
    }

    /**
     * validate the author is exists on the system or not
     * 
     * @param authotId the id of the author
     * @throws AuthorNotFoundException if author is not found in the system
     */
    private void validateAuthorExists(int authorId) throws AuthorNotFoundException {
        if (authorDAO.getAuthorById(authorId) == null) {
            throw new AuthorNotFoundException("Author with ID : " + String.valueOf(authorId) + " not found.");
        }
    }
    
    
    /**
     * validate the publication year of the book 
     * 
     * @param year the publication year of the book
     * @throws InvalidInputException if year is to old or year is a future
     */
    private void validatePublicationYear(int year) throws InvalidInputException {

        if (year > java.time.Year.now().getValue()) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }

        if (year < 1800) {
            throw new InvalidInputException("Publication year seems too old");
        }

    }
    
    /**
     * retrieves the book list written by specific author
     * 
     * @param authorId id of the author
     * @return list of the books written by author
     * @throws AuthorNotFoundException if no books found for the author
     */
    public List<Book> getBooksByAuthor(int authorId) {

        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorId() == authorId) {
                authorBooks.add(book);
            }
        }

        if (authorBooks.isEmpty()) {
            throw new AuthorNotFoundException("No books found for author with ID: " + authorId);
        }
        return authorBooks;
    }

    /**
     * reserves the book from given book stock quantity
     * 
     * @param bookId id of the book
     * @param quantity quantity to reserve the book
     * @throws BookNotFoundException if book is not found
     * @throws OutOfStockException if there is insufficient book stock
     */
    public void reserveBook(int bookId, int quantity) throws BookNotFoundException, OutOfStockException {

        Book book = getBookById(bookId);
        if (book.getStockQuantity() < quantity) {

            throw new OutOfStockException("Only " + book.getStockQuantity() + " units available for book ID : " + bookId);
        }
        book.setStockQuantity(book.getStockQuantity() - quantity);

    }
    
    /**
     * restore the book for given stock quantity
     * 
     * @param bookId id of the book
     * @param quantity quantity to restock the book
     * @throws BookNotFoundException if book is not found
     */
    public void restockBook(int bookId, int quantity) throws BookNotFoundException {

        Book book = getBookById(bookId);
        book.setStockQuantity(book.getStockQuantity() + quantity);

    }

}
