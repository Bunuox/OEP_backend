package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Question;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionFindRequest;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {
    Question createQuestion(QuestionCreationRequest questionCreationRequest);
    Question[] findQuestionsByExamId(QuestionFindRequest questionFindRequest);
}
