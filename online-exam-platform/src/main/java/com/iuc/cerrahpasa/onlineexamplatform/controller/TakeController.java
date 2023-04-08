package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.TakeCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.TakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("take/")
public class TakeController {

    @Autowired
    private TakeService takeService;

    @PostMapping("/createTake")
    public ResponseEntity<TakeCreationResponse> createCourse(@RequestBody TakeCreationRequest takeCreationRequest){
        Boolean success = takeService.createTake(takeCreationRequest);
        return new ResponseEntity<>(TakeCreationResponse.builder().success(success).build(), HttpStatus.OK);
    }
}
