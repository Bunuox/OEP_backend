package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Take;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.TakeFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.TakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("take/")
public class TakeController {

    @Autowired
    private TakeService takeService;

    @PostMapping("/createTake")
    public ResponseEntity<SuccessCreationResponse> createTake(@RequestBody TakeCreationRequest takeCreationRequest){
        Boolean success = takeService.createTake(takeCreationRequest);
        return new ResponseEntity<>(SuccessCreationResponse.builder().success(success).build(), HttpStatus.OK);
    }

    @PostMapping("/findTake")
    public ResponseEntity<List> findTake(@RequestBody TakeFindRequest takeFindRequest){
        Take[] takes = takeService.findTake(takeFindRequest);
        List<TakeFindResponse> responses = new ArrayList<>();

        for(Take t: takes){
            responses.add(TakeFindResponse.builder().instructorId(t.getInstructorId()).courseId(t.getCourseId()).build());
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
