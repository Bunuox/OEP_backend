package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentFindRequest;
import org.springframework.stereotype.Service;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentCreationRequest;

@Service
public interface StudentService {
	Boolean createStudent(StudentCreationRequest studentRequest);

//	Long findStudent(StudentFindRequest studentFindRequest);
}
