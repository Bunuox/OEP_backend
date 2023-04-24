package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamFindRequest {
    private Long courseId;
    private Long examId;
}
