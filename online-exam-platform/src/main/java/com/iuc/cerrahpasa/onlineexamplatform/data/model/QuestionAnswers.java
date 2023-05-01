package com.iuc.cerrahpasa.onlineexamplatform.data.model;

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
@Table(name = "QUESTION_ANSWERS")
public class QuestionAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
    private Long answerId;

    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "IS_CORRECT")
    private Boolean isCorrect;
}
