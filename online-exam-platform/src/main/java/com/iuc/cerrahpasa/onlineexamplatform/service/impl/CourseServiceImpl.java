package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Course;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.CourseFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.CourseRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course createCourse(CourseCreationRequest courseCreationRequest) {
        Course course = Course.builder()
                .courseName(courseCreationRequest.getCourseName())
                .description(courseCreationRequest.getDescription())
                .courseSemester(courseCreationRequest.getCourseSemester())
                .isActive(false).build();
        try {
            courseRepository.save(course);
            log.info("Course successfully created");
        } catch (Exception e) {
            log.info("course could not created due to: " + e.getLocalizedMessage());
            return course;
        }
        return course;
    }

    @Override
    public Course findCourse(CourseFindRequest courseFindRequest) {
        return courseRepository.findByCourseId(courseFindRequest.getCourseId());
    }
}
