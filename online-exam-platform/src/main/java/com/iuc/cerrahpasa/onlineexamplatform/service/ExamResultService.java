package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamResultCreationRequest;
import org.springframework.stereotype.Service;

@Service
public interface ExamResultService {
    Boolean createExamResult(ExamResultCreationRequest examResultCreationRequest);
}
