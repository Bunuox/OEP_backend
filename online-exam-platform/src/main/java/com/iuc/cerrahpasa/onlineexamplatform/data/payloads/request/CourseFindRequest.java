package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CourseFindRequest {
    @NotNull
    @NotBlank
    private Long courseId;
}
