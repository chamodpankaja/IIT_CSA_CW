/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.resource;

import com.mycompany.csa_cw.dao.BookDAO;
import com.mycompany.csa_cw.exceptions.AuthorNotFoundException;
import com.mycompany.csa_cw.exceptions.BookNotFoundException;
import com.mycompany.csa_cw.exceptions.InvalidInputException;
import com.mycompany.csa_cw.model.Book;
import java.util.List;
import java.util.Map;
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

    private BookDAO bookDAO = new BookDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {

        return bookDAO.getAllBooks();
    }

    @GET
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBooktById(@PathParam("bookId") int bookId) {

        return bookDAO.getBookById(bookId);
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addBook(Book book) {
//        try {
//            Book addedBook = bookDAO.addBook(book);
//            return Response.ok().entity(addedBook).build();
//        } catch (InvalidInputException e) {
//        return Response.status(Response.Status.BAD_REQUEST)
//                     .entity(Map.of(
//                         "error", e.getMessage()
//                     ))
//                     .build();
//        } 
//    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        Book addedBook = bookDAO.addBook(book);
        return Response.ok().entity(addedBook).build();
    }

//    @DELETE
//    @Path("/{bookId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteBook(@PathParam("bookId") int id) {
//        String message = bookDAO.deleteBook(id);
//        return Response.ok(Map.of("message", message)).build();
//    }

    
    @DELETE
    @Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("bookId") int id) {
        bookDAO.deleteBook(id);
        return Response.noContent().build(); // Returns HTTP 204
    }

    @PUT
    @Path("/{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("bookId") int id, Book book) {
        return bookDAO.updateBook(id, book);
    }

}
