package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswerCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/studentAnswer")
public class StudentAnswersController {

    @Autowired
    private StudentAnswersService studentAnswersService;

    @PutMapping("/createAndUpdateStudentAnswer")
    public ResponseEntity<SuccessCreationResponse> createAndUpdateStudentAnswer(@RequestBody StudentAnswerCreationRequest studentAnswerCreationRequest){
        try {
            studentAnswersService.createAndUpdateStudentAnswer(studentAnswerCreationRequest);
            return new ResponseEntity<>(SuccessCreationResponse.builder().success(true).build(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(SuccessCreationResponse.builder().success(false).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
