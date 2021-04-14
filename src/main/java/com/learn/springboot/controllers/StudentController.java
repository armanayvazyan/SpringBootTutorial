package com.learn.springboot.controllers;

import com.learn.springboot.entity.Student;
import com.learn.springboot.requests.CreateStudentRequest;
import com.learn.springboot.requests.DeleteStudentRequest;
import com.learn.springboot.requests.UpdateStudentRequest;
import com.learn.springboot.response.StudentResponse;
import com.learn.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<StudentResponse> getAllStudents(
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "id", required = false) Long id) {
        if(id != null) {
            List<Student> responses = new ArrayList<>();
            responses.add(studentService.getStudents(id));
            return responses.stream().map(StudentResponse::new).collect(Collectors.toList());
        }
        if(firstName != null && lastName != null) {
            List<Student> studentList = studentService.getStudentsByFirstName(firstName);
            return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
        }
        if(lastName != null) {
            List<Student> studentList = studentService.getStudentsByLastName(lastName);
            return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
        }
        if(firstName != null) {
            List<Student> studentList = studentService.getStudentsByLastName(lastName);
            return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
        }
        else {
            List<Student> studentList = studentService.getAllStudents();
            return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody CreateStudentRequest request) {
        return new ResponseEntity<>(new StudentResponse(studentService.createStudent(request)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest request) {
        return new StudentResponse(studentService.updateStudent(request));
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @PostMapping("/delete")
    public String deleteStudent(@Valid @RequestBody DeleteStudentRequest request) {
        return studentService.deleteStudent(request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }
}
