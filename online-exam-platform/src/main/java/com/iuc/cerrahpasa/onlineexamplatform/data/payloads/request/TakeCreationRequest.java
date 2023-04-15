package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TakeCreationRequest {

    @NotNull
    @NotBlank
    private Long courseId;

    @NotNull
    @NotBlank
    private Long studentId;

    private Long grade;
    
}
