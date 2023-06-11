package com.iuc.cerrahpasa.onlineexamplatform.data.model;

import com.iuc.cerrahpasa.onlineexamplatform.data.CompositeKeys.ExamResultsCompositeKeys;
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
@Table(name="EXAM_RESULTS")
@IdClass(ExamResultsCompositeKeys.class)
public class ExamResults {

    @Id
    @Column(name = "EXAM_ID")
    private Long examId;

    @Id
    @Column(name = "STUDENT_ID")
    private Long studentId;

    private Long grade;
}
