package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Instructor;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.InstructorCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.InstructorFindRequest;
import org.springframework.stereotype.Service;

@Service
public interface InstructorService {
    Boolean createInstructor(InstructorCreationRequest instructorCreationRequest);
    Instructor findInstructor(InstructorFindRequest instructorFindRequest);
}
