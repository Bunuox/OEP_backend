package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionAnswersCreationResponse {
    private Long questionId;
    private Long answerId;
}
