package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Teach;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.TeachCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.TeachRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.TeachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeachServiceImpl implements TeachService {

    @Autowired
    private TeachRepository teachRepository;

    @Override
    public Boolean createTeach(TeachCreationRequest teachCreationRequest) {
        Teach teach = Teach.builder()
                .instructorId(teachCreationRequest.getInstructorId())
                .courseId(teachCreationRequest.getCourseId())
                .isActive(false)
                .build();
        try {
            teachRepository.save(teach);
            log.info("Teach successfully created");
        } catch (Exception e) {
            log.info("Teach could not created due to: " + e.getLocalizedMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
