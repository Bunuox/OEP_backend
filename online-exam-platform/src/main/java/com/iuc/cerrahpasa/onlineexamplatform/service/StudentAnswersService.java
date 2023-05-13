package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswerCreationRequest;
import org.springframework.stereotype.Service;

@Service
public interface StudentAnswersService {

    void createAndUpdateStudentAnswer(StudentAnswerCreationRequest studentAnswerCreationRequest);
}
