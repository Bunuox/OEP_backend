package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Question;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionFindResponse {

    private Question question;
}
