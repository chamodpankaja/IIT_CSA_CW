/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.resource;

import com.mycompany.csa_cw.dao.BookDAO;
import com.mycompany.csa_cw.model.Book;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author chamodpankaja
 */
@Path("/books")
public class BookResource {

    // BookDAO instance to manage book related operations
    private BookDAO bookDAO = new BookDAO();

    /**
     * retrieves the all books in the system
     * 
     * @return List of the all books in the system
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {

        return bookDAO.getAllBooks();
    }

    
    /**
     * retrieve the specific book by book Id
     * 
     * @param bookId id of the book
     * @return book object of the given bookId
     */
    @GET
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBooktById(@PathParam("bookId") int bookId) {

        return bookDAO.getBookById(bookId);
    }

    /**
     * add a new book to the system
     * 
     * @param book the Book object to add
     * @return HTTP response of newly added book
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        Book addedBook = bookDAO.addBook(book);
        return Response.ok().entity(addedBook).build();
    }



    /**
     * delete book by book id
     * 
     * @param id id of the book
     * @return HTTP 204 no content response with successful deletion
     */
    @DELETE
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("bookId") int id) {
        bookDAO.deleteBook(id);
        return Response.noContent().build(); 
    }

    /**
     * update an existing book
     * 
     * @param id id of the book
     * @param book the updated book object
     * @return updated book object
     */
    @PUT
    @Path("/{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("bookId") int id, Book book) {
        return bookDAO.updateBook(id, book);
    }

}
