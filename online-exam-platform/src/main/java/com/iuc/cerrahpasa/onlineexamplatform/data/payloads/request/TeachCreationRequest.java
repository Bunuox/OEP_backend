package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeachCreationRequest {

    @NotNull
    @NotBlank
    private Long instructorId;

    @NotNull
    @NotBlank
    private Long courseId;
}
