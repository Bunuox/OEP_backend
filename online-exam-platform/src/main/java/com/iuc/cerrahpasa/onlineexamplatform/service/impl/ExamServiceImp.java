package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Exam;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.ExamRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExamServiceImp implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam createExam(ExamCreationRequest examCreationRequest) {
        Exam exam = Exam.builder()
                .examDescription(examCreationRequest.getExamDescription())
                .examDuration(examCreationRequest.getExamDuration())
                .examDate(examCreationRequest.getExamDate())
                .examName(examCreationRequest.getExamName())
                .examTime(examCreationRequest.getExamTime())
                .affect(examCreationRequest.getAffect())
                .courseId(examCreationRequest.getCourseId())
                .instructorId(examCreationRequest.getInstructorId())
                .isActive(false)
                .build();
        try {
            examRepository.save(exam);
            log.info("Exam crated");
        } catch (Exception e) {
            log.info("Exam could not created due to:" + e.getLocalizedMessage());
            return exam;
        }
        return exam;
    }

    @Override
    public Exam[] findMultipleExams(ExamFindRequest examFindRequest) {
        return examRepository.findByCourseId(examFindRequest.getCourseId());
    }

    public Exam findExam(ExamFindRequest examFindRequest){
        return examRepository.findByExamId(examFindRequest.getExamId());
    }
}
