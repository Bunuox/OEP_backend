package com.iuc.cerrahpasa.onlineexamplatform.service.impl;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.StudentAnswers;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswerCreationRequest;
import com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request.StudentAnswersFindRequest;
import com.iuc.cerrahpasa.onlineexamplatform.repository.StudentAnswersRepository;
import com.iuc.cerrahpasa.onlineexamplatform.service.StudentAnswersService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class StudentAnswersServiceImpl implements StudentAnswersService {

    @Autowired
    private StudentAnswersRepository studentAnswersRepository;

    @Override
    @Transactional
    public void createAndUpdateStudentAnswer(StudentAnswerCreationRequest studentAnswerCreationRequest) {
        StudentAnswers found = studentAnswersRepository.findByStudentIdAndQuestionId(studentAnswerCreationRequest.getStudentId(), studentAnswerCreationRequest.getQuestionId());
        if(found != null) {
            found.setAnswerId(studentAnswerCreationRequest.getAnswerId());
            found.setAnswerText(studentAnswerCreationRequest.getAnswerText());
            try {
                studentAnswersRepository.updateStudentAnswersByStudentIdAndQuestionId(found.getStudentId(), found.getQuestionId(), found.getAnswerText(), found.getAnswerId());
            } catch (Exception exception) {
                log.info("Student Answer could not updated due to: " + exception.getLocalizedMessage());
            }
        } else {
            StudentAnswers studentAnswer = StudentAnswers.builder()
                    .studentId(studentAnswerCreationRequest.getStudentId())
                    .questionId(studentAnswerCreationRequest.getQuestionId())
                    .answerText(studentAnswerCreationRequest.getAnswerText())
                    .answerId(studentAnswerCreationRequest.getAnswerId())
                    .build();
            try {
                studentAnswersRepository.save(studentAnswer);
                log.info("Student answer created successfully");
            } catch (Exception e){
                log.info("Student answer could not created due to:" + e.getLocalizedMessage());
            }
        }
    }

    @Override
    public ArrayList<StudentAnswers> findStudentAnswers(StudentAnswersFindRequest studentAnswersFindRequest) {
        ArrayList<StudentAnswers> studentAnswers = new ArrayList<>();
        for(Long questionId: studentAnswersFindRequest.getQuestionsId()){
            studentAnswers.add(studentAnswersRepository.findByStudentIdAndQuestionId(studentAnswersFindRequest.getStudentId(), questionId));
        }
        return studentAnswers;
    }
}