package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Course;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseFindRequest;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    Course createCourse(CourseCreationRequest courseCreationRequest);
    Course findCourse(CourseFindRequest courseFindRequest);
}
