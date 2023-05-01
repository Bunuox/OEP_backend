package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Question;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionCreationRequest;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {
    Question createQuestion(QuestionCreationRequest questionCreationRequest);

}
