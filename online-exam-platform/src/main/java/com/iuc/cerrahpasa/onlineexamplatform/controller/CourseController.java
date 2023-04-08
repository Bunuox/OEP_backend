package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Course;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.CourseCreationResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.CourseFindResponse;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("course/")
class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/createCourse")
    public ResponseEntity<CourseCreationResponse> createCourse(@RequestBody CourseCreationRequest courseCreationRequest){
        Boolean success = courseService.createCourse(courseCreationRequest);
        return new ResponseEntity<>(CourseCreationResponse.builder().success(success).build(), HttpStatus.OK);
    }

    @PostMapping("/findCourse")
    public ResponseEntity<CourseFindResponse> findCourse(@RequestBody CourseFindRequest courseFindRequest){
        Course course = courseService.findCourse(courseFindRequest);
        return new ResponseEntity<>(CourseFindResponse.builder().courseId(course.getCourseId()).build(), HttpStatus.OK);
    }
}