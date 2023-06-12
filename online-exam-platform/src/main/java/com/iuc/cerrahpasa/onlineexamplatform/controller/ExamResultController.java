package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.ExamResults;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamResultCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamResultsFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/examResult")
public class ExamResultController {

    @Autowired
    private ExamResultService examResultService;

    @PostMapping("/createResult")
    public ResponseEntity<SuccessCreationResponse> createExamResult(@RequestBody ExamResultCreationRequest examResultCreationRequest){
        Boolean success = examResultService.createExamResult(examResultCreationRequest);
        return new ResponseEntity<>(SuccessCreationResponse.builder().success(success).build(), HttpStatus.OK);
    }

    @PostMapping("/findStudentResultByExamId")
    public ResponseEntity<ArrayList<ExamResults>> findStudentResultByExamId(@RequestBody ExamResultsFindRequest examResultsFindRequest){
        return new ResponseEntity<>(examResultService.findStudentResultByExamId(examResultsFindRequest), HttpStatus.OK);
    }

    @PostMapping("/findExamResultsByExamId")
    public ResponseEntity<ExamResults[]> findExamResultsByExamId(@RequestBody ExamResultsFindRequest examResultsFindRequest){
        return new ResponseEntity<>(examResultService.findExamResultsByExamId(examResultsFindRequest), HttpStatus.OK);
    }
}
