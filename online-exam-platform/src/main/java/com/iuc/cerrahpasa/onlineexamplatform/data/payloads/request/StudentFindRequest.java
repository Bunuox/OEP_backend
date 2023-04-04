package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentFindRequest {
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;
}
