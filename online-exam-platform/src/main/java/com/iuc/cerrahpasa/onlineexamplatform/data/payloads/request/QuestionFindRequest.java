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
public class QuestionFindRequest {

    @NotNull
    @NotBlank
    private Long examId;
}
