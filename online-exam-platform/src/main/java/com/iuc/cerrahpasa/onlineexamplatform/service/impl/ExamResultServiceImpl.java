package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.ExamResults;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamResultCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.ExamResultRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.ExamResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExamResultServiceImpl implements ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Override
    public Boolean createExamResult(ExamResultCreationRequest examResultCreationRequest) {
        ExamResults examResults = ExamResults.builder()
                .examId(examResultCreationRequest.getExamId())
                .studentId(examResultCreationRequest.getStudentId())
                .grade(examResultCreationRequest.getGrade())
                .build();

        try {
            examResultRepository.save(examResults);
            log.info("Exam result created");
        } catch (Exception e){
            log.info("Exam result could not created due to: " + e.getLocalizedMessage());
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
