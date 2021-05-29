package com.learn.springboot.service;

import com.learn.springboot.entity.Address;
import com.learn.springboot.entity.Student;
import com.learn.springboot.entity.Subject;
import com.learn.springboot.repository.AddressRepository;
import com.learn.springboot.repository.StudentRepository;
import com.learn.springboot.repository.SubjectRepository;
import com.learn.springboot.requests.CreateStudentRequest;
import com.learn.springboot.requests.CreateSubjectRequest;
import com.learn.springboot.requests.DeleteStudentRequest;
import com.learn.springboot.requests.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getStudents(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return studentRepository.findAllByLastName(lastName).stream().filter(a -> a.getFirstName().equals(firstName)).collect(Collectors.toList());
        }
        if (firstName != null) {
            return studentRepository.findAllByFirstName(firstName);
        }
        if (lastName != null) {
            return studentRepository.findAllByLastName(lastName);
        }
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return studentRepository.findAllByFirstName(firstName);
    }


    public Student getStudents(long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(CreateStudentRequest request) {
        Student student = new Student(request);

        Address address = new Address();
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());
        addressRepository.save(address);

        student.setAddress(address);
        student = studentRepository.save(student);

        List<Subject> subjectList = new ArrayList<>();
        if(request.getSubjectsLearning() != null) {
            for (CreateSubjectRequest subjectRequest : request.getSubjectsLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(subjectRequest.getSubjectName());
                subject.setObtainedMarks(subjectRequest.getMarksObtained());
                subject.setStudent(student);

                subjectList.add(subject);
            }

            subjectRepository.saveAll(subjectList);
        }

        student.setLearningSubjects(subjectList);
        return student;
    }

    public Student updateStudent(UpdateStudentRequest request) {
        Student student = studentRepository.findById(request.getId()).get();
        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            student.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
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

    // Start from 0
    public List<Student> getStudentsByPage(int number) {
        Pageable pageable = PageRequest.of(number, 5);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getSortedList() {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        return studentRepository.findAll(sort);
    }

    public List<Student> getStudentsContainingFirstName(String firstName) {
        return studentRepository.findAllByFirstNameContaining(firstName);
    }

    public List<Student> getStudentsWithFirstNameStartingWith(String firstName) {
        return studentRepository.findAllByFirstNameStartingWith(firstName);
    }

    public List<Student> getStudentsWithFirstNameStartsWith(String firstName) {
        return studentRepository.findAllByFirstNameStartsWith(firstName);
    }

    public List<Student> getStudentsByCity(String cityName, boolean withJPQL) {
        if(withJPQL)
            return studentRepository.findAllByAddressCityQuery(cityName);
        return studentRepository.findAllByAddressCity(cityName);
    }

    public List<Student> getStudentsByCity(String cityName) {
        return getStudentsByCity(cityName, false);
    }
}
