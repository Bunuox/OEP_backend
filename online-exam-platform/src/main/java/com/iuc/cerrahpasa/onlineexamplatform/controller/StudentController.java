package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.StudentFindResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Student;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.StudentCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.EmailService;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/createStudent")
	public ResponseEntity<StudentCreationResponse> createStudent(@RequestBody StudentCreationRequest studentRequest) {

		Boolean success = studentService.createStudent(studentRequest);
		return new ResponseEntity<>(StudentCreationResponse.builder().success(success).build(), HttpStatus.OK);
	}

	@PostMapping("/findStudent")
	public ResponseEntity<StudentFindResponse> findStudent(@RequestBody StudentFindRequest studentFindRequest){
		Student student = studentService.findStudent(studentFindRequest);
		return new ResponseEntity<>(StudentFindResponse.builder().studentId(student.getStudentId()).build(), HttpStatus.OK);
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody String email) {

		// E-posta gönderimi için gerekli kodları burada yazın
		emailService.sendSimpleMessage(email, "Konu", "XD");

		return ResponseEntity.ok("E-posta başarıyla gönderildi.");
	}

}
