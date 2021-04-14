package com.learn.springboot.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateStudentRequest {

    @JsonProperty("first_name")
    @NotBlank(message = "first_name is missing")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
}
