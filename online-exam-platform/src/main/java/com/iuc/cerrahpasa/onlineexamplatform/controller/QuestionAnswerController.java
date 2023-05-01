package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.QuestionAnswers;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionAnswersCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.QuestionAnswersCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.QuestionAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/answer")
public class QuestionAnswerController {

    @Autowired
    private QuestionAnswersService questionAnswersService;

    @PostMapping("/createAnswer")
    public ResponseEntity<QuestionAnswersCreationResponse> createAnswer(@RequestBody QuestionAnswersCreationRequest questionAnswersCreationRequest){
        QuestionAnswers questionAnswers = questionAnswersService.createQuestionAnswer(questionAnswersCreationRequest);
        return new ResponseEntity<>(QuestionAnswersCreationResponse
                .builder()
                .questionId(questionAnswers.getQuestionId())
                .answerId(questionAnswers.getAnswerId())
                .build(), HttpStatus.OK);
    }
}
