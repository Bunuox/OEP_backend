package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeachFindResponse {
    private Long instructorId;
    private Long courseId;
}
