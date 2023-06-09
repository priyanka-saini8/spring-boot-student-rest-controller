package com.example.studentRest.rest;

import com.example.studentRest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    List<Student> theStudents;

    @PostConstruct
    public void loadStudents(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Daffy", "Duck"));
        theStudents.add(new Student("Priyanka", "Saini"));
        theStudents.add(new Student("Taylor", "Swift"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if( (studentId >= theStudents.size()) || (studentId < 0) ){
            throw new StudentNotFoundException("Student not found - " + studentId);
        }

        return theStudents.get(studentId);
    }
}
