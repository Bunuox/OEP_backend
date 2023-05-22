package com.iuc.cerrahpasa.onlineexamplatform.data.model;

import com.iuc.cerrahpasa.onlineexamplatform.data.CompositeKeys.StudentAnswersCompositeKeys;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="STUDENT_ANSWERS")
@IdClass(StudentAnswersCompositeKeys.class)
public class StudentAnswers {

    @Id
    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Id
    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "ANSWER_TEXT")
    private String answerText;

    @Column(name = "ANSWER_ID")
    private Long answerId;
}