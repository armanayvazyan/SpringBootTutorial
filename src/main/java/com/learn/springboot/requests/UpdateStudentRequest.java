package com.learn.springboot.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateStudentRequest {

    @NotNull(message = "id is missing")
    private Long id;

    private String firstName;

    private String lastName;
}
