package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Teach;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TeachCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TeachFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.TeachFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.TeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("teach/")
public class TeachController {

    @Autowired
    private TeachService teachService;

    @PostMapping("/createTeach")
    public ResponseEntity<SuccessCreationResponse> createTeach(@RequestBody TeachCreationRequest teachCreationRequest){
        Boolean success = teachService.createTeach(teachCreationRequest);
        return new ResponseEntity<>(SuccessCreationResponse.builder().success(success).build(), HttpStatus.OK);
    }

    @PostMapping("/findTeach")
    public ResponseEntity<List> findTeach(@RequestBody TeachFindRequest teachFindRequest){
        Teach[] teach = teachService.findTeach(teachFindRequest);
        List<TeachFindResponse> responses = new ArrayList<>();

        for(Teach t: teach){
            responses.add(TeachFindResponse.builder().courseId(t.getCourseId()).instructorId(t.getInstructorId()).build());
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}
