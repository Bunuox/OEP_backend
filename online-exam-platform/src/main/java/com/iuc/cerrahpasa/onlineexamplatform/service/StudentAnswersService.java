package com.iuc.cerrahpasa.onlineexamplatform.service;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.StudentAnswers;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswerCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswersFindRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface StudentAnswersService {

    void createAndUpdateStudentAnswer(StudentAnswerCreationRequest studentAnswerCreationRequest);

    ArrayList<StudentAnswers> findStudentAnswers(StudentAnswersFindRequest studentAnswersFindRequest);
}