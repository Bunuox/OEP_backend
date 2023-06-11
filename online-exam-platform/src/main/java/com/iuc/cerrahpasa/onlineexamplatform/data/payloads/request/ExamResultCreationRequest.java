package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultCreationRequest {

    @NotNull
    @NotBlank
    private Long examId;

    @NotNull
    @NotBlank
    private Long studentId;

    private Long grade;

}
