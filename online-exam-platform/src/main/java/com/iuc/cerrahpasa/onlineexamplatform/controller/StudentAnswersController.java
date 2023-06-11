package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.StudentAnswers;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswerCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswersFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.StudentAnswersFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @PostMapping("/findStudentAnswersByQuestion")
    public ResponseEntity<ArrayList<StudentAnswers>> findStudentAnswersByQuestion(@RequestBody StudentAnswersFindRequest studentAnswersFindRequest){
        try {
            return new ResponseEntity<>(studentAnswersService.findStudentAnswers(studentAnswersFindRequest), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}