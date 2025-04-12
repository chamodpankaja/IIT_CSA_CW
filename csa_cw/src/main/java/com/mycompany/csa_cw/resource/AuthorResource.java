/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.resource;

import com.mycompany.csa_cw.dao.AuthorDAO;
import com.mycompany.csa_cw.dao.BookDAO;
import com.mycompany.csa_cw.exceptions.AuthorNotFoundException;
import com.mycompany.csa_cw.model.Author;
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

@Path("/authors")
public class AuthorResource {
    
    
    private AuthorDAO authorDAO = new AuthorDAO();
    private BookDAO bookDAO  = new BookDAO();
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAllAuthors(){

        return authorDAO.getAllAuthors();
    }
    
    
    @GET
    @Path("/{authorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Author getAuthorById(@PathParam("authorId") int authorId) {
        
        return authorDAO.getAuthorById(authorId);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(Author author){
    
        Author addAuthor =  authorDAO.addAuthor(author);
        return Response
                .status(Response.Status.CREATED)
                .entity(addAuthor)
                .build();
        
    }
    
    
    @DELETE
    @Path("/{authorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(@PathParam("authorId") int authorId){
        
        String message = authorDAO.deleteAuthor(authorId);
        return Response.ok(Map.of("message",message)).build();
                
    
        
    }
    
    @PUT
    @Path("/{authorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Author updateaAuthor(@PathParam("authorId") int authorId, Author author){
    
        return authorDAO.updateAuthor(authorId, author);
    }
    
    
    
//    @GET
//    @Path("/{authorId}/books")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getBooksByAuthor(@PathParam("authorId") int authorId) {
//        try {
//            List<Book> books = authorDAO.getBookByAuthor(authorId);
//            return Response.ok(books).build();
//        } catch (AuthorNotFoundException e) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity(Map.of("error", "No books found", "message", e.getMessage()))
//                    .build();
//        }
//    }
//    
    
    @GET
    @Path("/{authorId}/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooksByAuthor(@PathParam("authorId") int authorId) {
        try {
            // First check if author exists
            if (!authorDAO.authorExists(authorId)) {
                throw new AuthorNotFoundException("Author with ID " + authorId + " not found");
            }
            
            // Then get books (may be empty)
            List<Book> books = bookDAO.getBooksByAuthor(authorId);
            return Response.ok(books).build();
            
        } catch (AuthorNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                         .entity(Map.of(
                             "error", "Author not found",
                             "message", e.getMessage()
                         ))
                         .build();
        }
    }
    
}


