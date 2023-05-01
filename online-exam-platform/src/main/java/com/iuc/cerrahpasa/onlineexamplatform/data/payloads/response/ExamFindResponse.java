package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Exam;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExamFindResponse {

    private Exam exam;
}
