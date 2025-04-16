/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.mappers;

import com.mycompany.csa_cw.exceptions.AuthorNotFoundException;
import com.mycompany.csa_cw.exceptions.BookNotFoundException;
import com.mycompany.csa_cw.exceptions.CartNotFoundException;
import com.mycompany.csa_cw.exceptions.CustomerNotFoundException;
import com.mycompany.csa_cw.exceptions.InvalidInputException;
import com.mycompany.csa_cw.exceptions.OutOfStockException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author chamodpankaja
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        Map<String, String> errorResponse = new HashMap<>();

        if (exception instanceof BookNotFoundException) {
            errorResponse.put("error", "Book Not Found");
            errorResponse.put("message", exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else if (exception instanceof AuthorNotFoundException) {
            errorResponse.put("error", "Author Not Found");
            errorResponse.put("message", exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else if (exception instanceof InvalidInputException) {
            errorResponse.put("error", "Invalid Input");
            errorResponse.put("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else if(exception instanceof  CustomerNotFoundException){
            errorResponse.put("error", "Customer Not Found");
            errorResponse.put("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        }else if(exception instanceof CartNotFoundException){
            errorResponse.put("error", "Cart Not Found");
            errorResponse.put("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }else if(exception instanceof OutOfStockException){
            errorResponse.put("error", "Out Of Stock");
            errorResponse.put("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {

            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
