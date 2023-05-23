package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class InstructorFindResponse {
    private Long instructorId;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String department;
}
