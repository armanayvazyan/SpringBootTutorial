package com.learn.springboot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.springboot.entity.Subject;

public class SubjectResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("subjectName")
    private String subjectName;

    @JsonProperty("marksObtained")
    private Double marksObtained;

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.marksObtained = subject.getObtainedMarks();
    }
}
