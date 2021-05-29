package com.learn.springboot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.springboot.entity.Student;
import com.learn.springboot.entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class StudentResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String fullName;

    @JsonProperty("street")
    private String street;

    @JsonProperty("city")
    private String city;

    @JsonProperty("subjects")
    private List<SubjectResponse> learningSubjects;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.fullName = firstName + lastName;
        try {
            if(student.getAddress().getCity() != null)
                this.city = student.getAddress().getCity();
        } catch (NullPointerException e) {
            //
        }
        try {
            if (student.getAddress().getStreet() != null)
                this.street = student.getAddress().getStreet();
        } catch (NullPointerException e ){
            //
        }
        if(!student.getLearningSubjects().isEmpty()) {
            learningSubjects = new ArrayList<>();
            for(Subject subject : student.getLearningSubjects()) {
                learningSubjects.add(new SubjectResponse(subject));
            }
        }
    }
}
