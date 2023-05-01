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
@Table(name = "QUESTIONS")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "EXAM_ID")
    private Long examId;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "POINT")
    private Long point;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}
