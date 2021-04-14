package com.learn.springboot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.springboot.entity.Student;

public class StudentResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
    }
}
