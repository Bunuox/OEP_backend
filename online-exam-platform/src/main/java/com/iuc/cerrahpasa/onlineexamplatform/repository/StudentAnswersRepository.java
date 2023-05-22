package com.iuc.cerrahpasa.onlineexamplatform.repository;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.StudentAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswersRepository extends JpaRepository<StudentAnswers, Long> {
    StudentAnswers findByStudentIdAndQuestionId(Long studentId, Long questionId);

    @Modifying
    @Query(value = "UPDATE StudentAnswers sa " +
            "SET sa.answerText =:answerText, sa.answerId =:answerId " +
            "WHERE sa.studentId =:studentId AND sa.questionId =:questionId")
    void updateStudentAnswersByStudentIdAndQuestionId(@Param("studentId") Long studentId, @Param("questionId") Long questionId, @Param("answerText") String answerText, @Param("answerId") Long answerId);
}