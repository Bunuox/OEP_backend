package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.QuestionAnswers;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionAnswersCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionAnswersFindRequest;
import org.springframework.stereotype.Service;

@Service
public interface QuestionAnswersService {
    QuestionAnswers createQuestionAnswer(QuestionAnswersCreationRequest questionAnswersCreationRequest);

    QuestionAnswers[] findQuestionAnswersByQuestionId(QuestionAnswersFindRequest questionAnswersFindRequest);
}
