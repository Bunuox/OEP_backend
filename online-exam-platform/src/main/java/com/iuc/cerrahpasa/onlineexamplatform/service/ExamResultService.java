package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.ExamResults;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamResultCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.ExamResultsFindRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ExamResultService {
    Boolean createExamResult(ExamResultCreationRequest examResultCreationRequest);

    ArrayList<ExamResults> findStudentResultByExamId(ExamResultsFindRequest examResultsFindRequest);

    ExamResults[] findExamResultsByExamId(ExamResultsFindRequest examResultsFindRequest);
}
