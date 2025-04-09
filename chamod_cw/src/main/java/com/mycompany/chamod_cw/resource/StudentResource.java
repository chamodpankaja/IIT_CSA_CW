/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chamod_cw.resource;

import com.mycompany.chamod_cw.DAO.StudentDAO;
import com.mycompany.chamod_cw.model.Student;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
//    @GET
//    @Path("/{studentId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Student getStudentById(@PathParam("studentId") int studentId) {
//        logger.info("GET request for student with ID: {}", studentId);
//
//        // Use Stream API to filter the student by ID and handle exception if not found
//        return studentDAO.getAllStudents().stream()
//                .filter(student -> student.getId() == studentId) // Filter by student ID
//                .findFirst() // Get the first student that matches
//                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));
//    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addStudent(Student student ){
    
        studentDAO.addStudent(student);
    
    }
}
