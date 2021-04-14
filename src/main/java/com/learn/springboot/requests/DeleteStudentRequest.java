package com.learn.springboot.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteStudentRequest {
    @NotNull(message = "id is missing")
    private Long id;
}
