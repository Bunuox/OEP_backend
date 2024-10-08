package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CourseCreationRequest {

    @NotNull
    @NotBlank
    private String courseName;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String courseSemester;
}