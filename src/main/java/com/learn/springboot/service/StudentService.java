package com.learn.springboot.service;

import com.learn.springboot.entity.Student;
import com.learn.springboot.repository.StudentRepository;
import com.learn.springboot.requests.CreateStudentRequest;
import com.learn.springboot.requests.DeleteStudentRequest;
import com.learn.springboot.requests.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return studentRepository.findAllByFirstName(firstName);
    }
    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findAllByLastName(lastName);
    }

    public Student getStudents(long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(CreateStudentRequest request) {
        Student student = new Student(request);
        student = studentRepository.save(student);
        return student;
    }

    public Student updateStudent(UpdateStudentRequest request) {
        Student student = studentRepository.findById(request.getId()).get();
        if(request.getFirstName() != null && !request.getFirstName().isEmpty()){
            student.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null && !request.getLastName().isEmpty()){
            student.setLastName(request.getLastName());
        }
        return studentRepository.save(student);
    }

    public String deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return "Student has been deleted successfully";
    }

    public String deleteStudent(DeleteStudentRequest request) {
        studentRepository.deleteById(request.getId());
        return "Student has been deleted successfully";
    }
}
