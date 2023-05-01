package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Exam;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.ExamCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.ExamFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamService examService;

    @PostMapping("/createExam")
    public ResponseEntity<ExamCreationResponse> createExam(@RequestBody ExamCreationRequest examCreationRequest){
        Exam createdExam = examService.createExam(examCreationRequest);
        return new ResponseEntity<>(ExamCreationResponse.builder().examId(createdExam.getExamId()).build(), HttpStatus.OK);
    }

    @PostMapping("/findExam")
    public ResponseEntity<ExamFindResponse> findExam(@RequestBody ExamFindRequest examFindRequest){
        Exam exam = examService.findExam(examFindRequest);
        return new ResponseEntity<>(ExamFindResponse.builder().exam(exam).build(), HttpStatus.OK);
    }

    @PostMapping("/findExamByCourseId")
    public ResponseEntity<Exam[]> findExamByCourseId(@RequestBody ExamFindRequest examFindRequest){
        Exam[] exams = examService.findMultipleExams(ExamFindRequest.builder().courseId(examFindRequest.getCourseId()).build());
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

}
