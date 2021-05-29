package com.learn.springboot.entity;

import com.learn.springboot.requests.CreateStudentRequest;
import com.learn.springboot.requests.UpdateStudentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "StudentInfo")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "student")
    private List<Subject> learningSubjects;

    @Transient
    private String fullName;

    public Student(CreateStudentRequest request) {
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.fullName = firstName + lastName;
    }
}
