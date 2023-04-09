package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Instructor;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.InstructorCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.InstructorFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.InstructorFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/createInstructor")
    public ResponseEntity<SuccessCreationResponse> createInstructor(@RequestBody InstructorCreationRequest instructorCreationRequest){
        Boolean success = instructorService.createInstructor(instructorCreationRequest);
        return new ResponseEntity<>(SuccessCreationResponse.builder().success(success).build(), HttpStatus.OK);
    }

    @PostMapping("/findInstructor")
    public ResponseEntity<InstructorFindResponse> findInstructor(@RequestBody InstructorFindRequest instructorFindRequest){
        Instructor instructor = instructorService.findInstructor(instructorFindRequest);
        return new ResponseEntity<>(InstructorFindResponse.builder().instructorId(instructor.getInstructorId()).build(), HttpStatus.OK);
    }
}
