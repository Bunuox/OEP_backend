package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Course;
import com.iuc.cerrahpasa.onlineexamplatform.data.model.Take;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TakeFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.StudentFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.CourseService;
import com.iuc.cerrahpasa.onlineexamplatform.service.TakeService;
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
import com.iuc.cerrahpasa.onlineexamplatform.service.EmailService;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private TakeService takeService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/createStudent")
	public ResponseEntity<SuccessCreationResponse> createStudent(@RequestBody StudentCreationRequest studentRequest) {

		Boolean success = studentService.createStudent(studentRequest);
		return new ResponseEntity<>(SuccessCreationResponse.builder().success(success).build(), HttpStatus.OK);
	}

	@PostMapping("/findStudent")
	public ResponseEntity<StudentFindResponse> findStudent(@RequestBody StudentFindRequest studentFindRequest){
		Student student = studentService.findStudent(studentFindRequest);
		return new ResponseEntity<>(StudentFindResponse.builder().studentId(student.getStudentId()).build(), HttpStatus.OK);
	}

	@PostMapping("/studentCourses")
	public ResponseEntity<List> studentCourses(@RequestBody TakeFindRequest takeFindRequest){
		Take[] takes = takeService.findTake(takeFindRequest);
		List<Course> courses = new ArrayList<>();

		for(Take t: takes){
			courses.add(courseService.findCourse(CourseFindRequest.builder().courseId(t.getCourseId()).build()));
		}

		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody String email) {

		// E-posta gönderimi için gerekli kodları burada yazın
		emailService.sendSimpleMessage(email, "Konu", "XD");

		return ResponseEntity.ok("E-posta başarıyla gönderildi.");
	}

}
