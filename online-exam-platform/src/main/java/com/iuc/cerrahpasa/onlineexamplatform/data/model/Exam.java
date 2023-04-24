package com.iuc.cerrahpasa.onlineexamplatform.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EXAMS")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXAM_ID")
    private Long examId;

    @Column(name = "COURSE_ID")
    private Long courseId;

    @Column(name = "INSTRUCTOR_ID")
    private Long instructorId;

    @Column(name = "EXAM_NAME")
    private String examName;

    @Column(name = "EXAM_DATE")
    private LocalDate examDate;

    @Column(name = "EXAM_DURATION")
    private Long examDuration;

    @Column(name = "AFFECT")
    private Long affect;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "EXAM_DESCRIPTION")
    private String examDescription;

    @Column(name = "EXAM_TIME")
    private Time examTime;
}
