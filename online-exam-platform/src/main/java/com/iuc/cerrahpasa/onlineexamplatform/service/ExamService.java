package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Exam;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamFindRequest;
import org.springframework.stereotype.Service;

@Service
public interface ExamService {
    Exam createExam(ExamCreationRequest examCreationRequest);
    Exam findExam(ExamFindRequest examFindRequest);
}