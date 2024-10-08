package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentFindRequest;
import org.springframework.stereotype.Service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Student;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentCreationRequest;

@Service
public interface StudentService {
	Boolean createStudent(StudentCreationRequest studentRequest);

	Student findStudent(StudentFindRequest studentFindRequest);
}
