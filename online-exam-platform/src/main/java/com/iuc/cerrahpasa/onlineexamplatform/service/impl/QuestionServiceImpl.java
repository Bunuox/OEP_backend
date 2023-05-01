package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Question;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.QuestionRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question createQuestion(QuestionCreationRequest questionCreationRequest) {
        Question question = Question.builder()
                .examId(questionCreationRequest.getExamId())
                .text(questionCreationRequest.getText())
                .point(questionCreationRequest.getPoint())
                .type(questionCreationRequest.getType())
                .isActive(Boolean.FALSE)
                .build();
        try {
            questionRepository.save(question);
            log.info("Question created");
        } catch (Exception e) {
            log.info("Question could not created due to: " + e.getLocalizedMessage());
            return question;
        }
        return question;
    }
}
