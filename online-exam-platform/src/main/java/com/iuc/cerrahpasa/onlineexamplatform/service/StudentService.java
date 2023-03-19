package com.iuc.cerrahpasa.onlineexamplatform.service;

import org.springframework.stereotype.Component;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentRequest;

@Component
public interface StudentService {
	Boolean createStudent(StudentRequest studentRequest);
}
