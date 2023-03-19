package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Student;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.StudentRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Boolean createStudent(StudentRequest studentRequest) {
		Student student = Student.builder().identificationNo(studentRequest.getIdentificationNo())
										   .firstName(studentRequest.getFirstName())
										   .lastName(studentRequest.getLastName())
										   .email(studentRequest.getEmail())
										   .gender(studentRequest.getGender())
										   .dateOfBirth(studentRequest.getDateOfBirth())
										   .isActive(studentRequest.getIsActive())
										   .password(studentRequest.getPassword())
										   .build();

		try {
			studentRepository.save(student);
		} catch(Exception e) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
