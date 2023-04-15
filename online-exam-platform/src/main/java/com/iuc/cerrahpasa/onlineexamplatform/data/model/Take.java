package com.iuc.cerrahpasa.onlineexamplatform.data.model;

import com.iuc.cerrahpasa.onlineexamplatform.data.CompositeKeys.TakeCompositeKeys;
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
@Table(name="TAKE")
@IdClass(TakeCompositeKeys.class)
public class Take {

    @Id
    @Column(name = "COURSE_ID")
    private Long courseId;

    @Id
    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "GRADE")
    private Long grade;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}
