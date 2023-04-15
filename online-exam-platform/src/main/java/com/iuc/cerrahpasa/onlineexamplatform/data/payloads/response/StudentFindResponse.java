package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Student;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentFindResponse {
    private Student student;
}
