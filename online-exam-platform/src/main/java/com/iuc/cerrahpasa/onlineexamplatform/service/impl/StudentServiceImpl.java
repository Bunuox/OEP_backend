package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentFindRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Student;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.StudentRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Boolean createStudent(StudentCreationRequest studentRequest) {
		Student student = Student.builder().identificationNo(studentRequest.getIdentificationNo())
										   .firstName(studentRequest.getFirstName())
										   .lastName(studentRequest.getLastName())
										   .email(studentRequest.getEmail())
										   .gender(studentRequest.getGender())
										   .dateOfBirth(studentRequest.getDateOfBirth())
										   .isActive(Boolean.FALSE)
										   .password(generateRandomPassword())
										   .build();
		try {
			studentRepository.save(student);
			log.info("Student record has been generated.");
			
		} catch(Exception e) {
			log.info("Student record has not been generated.");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	private String generateRandomPassword() {
		return " "; //TODO: buraya daha sonrabak.
	}

	@Override
	public Student findStudent(StudentFindRequest studentFindRequest) {
		
		return studentRepository.findByEmail(studentFindRequest.getEmail());
	}
}
