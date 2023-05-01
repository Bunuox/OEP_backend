package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.QuestionAnswers;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionAnswersCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.QuestionAnswersRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.QuestionAnswersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuestionAnswersServiceImpl implements QuestionAnswersService {

    @Autowired
    private QuestionAnswersRepository questionAnswersRepository;

    @Override
    public QuestionAnswers createQuestionAnswer(QuestionAnswersCreationRequest questionAnswersCreationRequest) {
        QuestionAnswers questionAnswer = QuestionAnswers.builder()
                .questionId(questionAnswersCreationRequest.getQuestionId())
                .text(questionAnswersCreationRequest.getText())
                .isCorrect(questionAnswersCreationRequest.getIsCorrect())
                .build();

        try {
            questionAnswersRepository.save(questionAnswer);
            log.info("Question Answer created");
        } catch(Exception e) {
            log.info("Question answer could not created due to : " + e.getLocalizedMessage());
            return QuestionAnswers.builder().build();
        }

        return questionAnswer;
    }
}
