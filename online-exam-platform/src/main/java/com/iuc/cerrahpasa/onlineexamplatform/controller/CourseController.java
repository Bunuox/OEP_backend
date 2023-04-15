package com.iuc.cerrahpasa.onlineexamplatform.controller;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Course;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.response.CourseCreationResponse;
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
        Course createdCourse = courseService.createCourse(courseCreationRequest);
        return new ResponseEntity<>(CourseCreationResponse.builder().courseId(createdCourse.getCourseId()).build(), HttpStatus.OK);
    }

    @PostMapping("/findCourse")
    public ResponseEntity<Course> findCourse(@RequestBody CourseFindRequest courseFindRequest){
        Course course = courseService.findCourse(courseFindRequest);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}