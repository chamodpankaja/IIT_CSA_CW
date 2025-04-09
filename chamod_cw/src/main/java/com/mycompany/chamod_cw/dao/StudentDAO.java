/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chamod_cw.dao;

import com.mycompany.chamod_cw.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chamodpankaja
 */
public class StudentDAO {
    
     private static List<Student> students =  new ArrayList<>();
    
    
    static{
        students.add(new Student(1,"john doe"));
        students.add(new Student(2,"chamod pankaja"));
    }
    
    
    public List<Student> getAllStudents(){
        return students;
    }
    
    public Student getStudentByID(int id){
    
        for(Student student : students){
        
            if(student.getId() == id){
            
                return student;
            }
        }
        return null;
    }
    
    public void addStudent(Student student){
    
       int newUserId = getNextUserId();
       student.setId(newUserId);
       students.add(student);
    }
    
     public int getNextUserId(){
    
       int maxUserId = Integer.MIN_VALUE;
       for(Student student : students){
           int userId = student.getId();
           if(userId > maxUserId){
               maxUserId = userId;
                       
           }
       }
       return maxUserId +1;
    }

    
    
}
