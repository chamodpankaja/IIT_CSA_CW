/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa_cw.resource;

import com.mycompany.csa_cw.dao.StudentDAO;
import com.mycompany.csa_cw.model.Student;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author chamodpankaja
 */
@Path("/students")
public class StudentResource {
    
    private StudentDAO studentDAO = new StudentDAO();
    private static final Logger logger =LoggerFactory.getLogger(StudentResource.class);
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<Student> getAllStudents(){
        
        return studentDAO.getAllStudents();
    }
    
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("studentId") int studentId){
        logger.info("GET request for all students");
        return studentDAO.getStudentByID(studentId);
    
    }
   


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addStudent(Student student ){
    
        studentDAO.addStudent(student);
    
    }
    
    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateStudent(@PathParam("studentId") int studentId, Student updatedStudent){
    
        Student existingStudent = studentDAO.getStudentByID(studentId);
        
        if(existingStudent != null){
        
            updatedStudent.setId(studentId);
            studentDAO.updateStudent(updatedStudent);
        }

    }
    
    @DELETE
    @Path("/{studentId}")
    public void deleteStudent(@PathParam("studentId")int studentId){
        studentDAO.deleteStudent(studentId);
    }
    
        
   
}

