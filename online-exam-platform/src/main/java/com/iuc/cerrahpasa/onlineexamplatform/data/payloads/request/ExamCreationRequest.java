package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Data
public class ExamCreationRequest {

    @NotNull
    @NotBlank
    private Long courseId;

    @NotNull
    @NotBlank
    private Long instructorId;

    @NotNull
    @NotBlank
    private String examName;

    @NotNull
    @NotBlank
    private LocalDate examDate;

    @NotNull
    @NotBlank
    private Long examDuration;

    @NotNull
    @NotBlank
    private Long affect;

    @NotNull
    @NotBlank
    private String examDescription;

    @NotNull
    @NotBlank
    private Time examTime;

}
