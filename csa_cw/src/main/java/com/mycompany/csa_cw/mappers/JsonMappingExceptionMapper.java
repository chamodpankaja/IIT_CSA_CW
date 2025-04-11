/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.mappers;

/**
 *
 * @author chamodpankaja
 */
import com.fasterxml.jackson.databind.JsonMappingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    @Override
    public Response toResponse(JsonMappingException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid input type: " + exception.getOriginalMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(errorResponse)
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}