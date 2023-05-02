package com.iuc.cerrahpasa.onlineexamplatform.repository;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.QuestionAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswersRepository extends JpaRepository<QuestionAnswers, Long> {

    QuestionAnswers[] findAllByQuestionId(Long questionId);
}
