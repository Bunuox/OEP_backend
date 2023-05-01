package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class QuestionCreationRequest {

    @NotBlank
    @NotNull
    private Long examId;

    @NotBlank
    @NotNull
    private String text;

    @NotBlank
    @NotNull
    private String type;

    @NotBlank
    @NotNull
    private Long point;
}
