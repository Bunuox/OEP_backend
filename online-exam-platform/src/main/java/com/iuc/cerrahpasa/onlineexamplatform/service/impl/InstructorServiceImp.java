package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Instructor;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.InstructorCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.InstructorFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.InstructorRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.InstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InstructorServiceImp implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public Boolean createInstructor(InstructorCreationRequest instructorCreationRequest){
        Instructor instructor = Instructor.builder()
                .firstName(instructorCreationRequest.getFirstName())
                .lastName(instructorCreationRequest.getLastName())
                .email(instructorCreationRequest.getEmail())
                .gender(instructorCreationRequest.getGender())
                .dateOfBirth(instructorCreationRequest.getDateOfBirth())
                .department(instructorCreationRequest.getDepartment())
                .password("")
                .isActive(Boolean.FALSE)
                .build();
        try {
            instructorRepository.save(instructor);
            log.info("instructor created");
        } catch (Exception e) {
            log.info("instructor could not created due to:" + e.getLocalizedMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Instructor findInstructor(InstructorFindRequest instructorFindRequest){
        return instructorRepository.findByEmail(instructorFindRequest.getEmail());
    }

}
