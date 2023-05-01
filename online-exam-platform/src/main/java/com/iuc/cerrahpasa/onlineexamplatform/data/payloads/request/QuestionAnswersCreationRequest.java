package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class QuestionAnswersCreationRequest {

    @NotBlank
    @NotNull
    private Long questionId;

    @NotBlank
    @NotNull
    private String text;

    @NotBlank
    @NotNull
    private Boolean isCorrect;
}
