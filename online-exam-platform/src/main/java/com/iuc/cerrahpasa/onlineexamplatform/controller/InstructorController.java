package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Course;
import com.iuc.cerrahpasa.onlineexamplatform.data.model.Exam;
import com.iuc.cerrahpasa.onlineexamplatform.data.model.Instructor;
import com.iuc.cerrahpasa.onlineexamplatform.data.model.Teach;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.*;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.InstructorFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.SuccessCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.service.CourseService;
import com.iuc.cerrahpasa.onlineexamplatform.service.ExamService;
import com.iuc.cerrahpasa.onlineexamplatform.service.InstructorService;
import com.iuc.cerrahpasa.onlineexamplatform.service.TeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private TeachService teachService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ExamService examService;

    @PostMapping("/createInstructor")
    public ResponseEntity<SuccessCreationResponse> createInstructor(@RequestBody InstructorCreationRequest instructorCreationRequest){
        Boolean success = instructorService.createInstructor(instructorCreationRequest);
        return new ResponseEntity<>(SuccessCreationResponse.builder().success(success).build(), HttpStatus.OK);
    }

    @PostMapping("/findInstructor")
    public ResponseEntity<InstructorFindResponse> findInstructor(@RequestBody InstructorFindRequest instructorFindRequest){
        Instructor instructor = instructorService.findInstructor(instructorFindRequest);
        return new ResponseEntity<>(InstructorFindResponse.builder()
                .email(instructor.getEmail())
                .instructorId(instructor.getInstructorId())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/instructorCourses")
    public ResponseEntity<List> instructorCourses(@RequestBody TeachFindRequest teachFindRequest){
        Teach[] teaches = teachService.findTeach(teachFindRequest);
        List<Course> courses = new ArrayList<>();
        for(Teach t: teaches) {
            courses.add(courseService.findCourse(CourseFindRequest.builder().courseId(t.getCourseId()).build()));
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/instructorExams")
    public ResponseEntity<List> instructorExams(@RequestBody TeachFindRequest teachFindRequest){
        Teach[] teaches = teachService.findTeach(teachFindRequest);
        List<Exam> exams = new ArrayList<>();

        for(Teach t: teaches){
            Exam[] courseExams = examService.findMultipleExams(ExamFindRequest.builder().courseId(t.getCourseId()).build());
            exams.addAll(Arrays.asList(courseExams));
        }

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }
}
