package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructorFindResponse {
    private Long instructorId;
    private String email;
}
