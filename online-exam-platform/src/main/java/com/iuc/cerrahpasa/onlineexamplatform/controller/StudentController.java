package com.iuc.cerrahpasa.onlineexamplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.StudentCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/createStudent")
	public ResponseEntity<StudentCreationResponse> message(@RequestBody StudentRequest studentRequest) {

		Boolean success = studentService.createStudent(studentRequest);
		return new ResponseEntity<>(StudentCreationResponse.builder().success(success).build(), HttpStatus.OK);
	}

}
