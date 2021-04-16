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
            @RequestParam(value = "firstName", required = false) String firstName) {
        List<Student> studentList = studentService.getStudents(firstName, lastName);
        return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
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
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Some parameters are invalid")
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

    @GetMapping("/page/{number}")
    public List<StudentResponse> getStudentsByPage(@PathVariable int number) {
        List<Student> studentsList = studentService.getStudentsByPage(number);
        return studentsList.stream().map(StudentResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/sorted")
    public List<StudentResponse> getStudentInAscendingOrder() {
        List<Student> studentList = studentService.getSortedList();
        return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/sorted/contains/{contains}")
    public List<StudentResponse> getStudentsContainingFirstName(@PathVariable String contains) {
        List<Student> studentList = studentService.getStudentsContainingFirstName(contains);
        return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/sorted/starts/{starts}")
    public List<StudentResponse> getStudentsWithFirstNameStartsWith(@PathVariable String starts) {
        List<Student> studentList = studentService.getStudentsWithFirstNameStartsWith(starts);
        return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/sorted/starting/{starting}")
    public List<StudentResponse> getStudentsWithFirstNameStartingWith(@PathVariable String starting) {
        List<Student> studentList = studentService.getStudentsWithFirstNameStartingWith(starting);
        return studentList.stream().map(StudentResponse::new).collect(Collectors.toList());
    }
}