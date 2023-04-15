package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.Data;

@Data
public class TeachFindRequest {
    private Long instructorId;
    private Long courseId;
}
