package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Question;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.QuestionCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.QuestionCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/createQuestion")
    public ResponseEntity<QuestionCreationResponse> createQuestion(@RequestBody QuestionCreationRequest questionCreationRequest){
        Question success = questionService.createQuestion(questionCreationRequest);
        return new ResponseEntity<>(QuestionCreationResponse.builder()
                .questionId(success.getQuestionId())
                .examId(success.getExamId())
                .build(), HttpStatus.OK);
    }
}
