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
@Table(name="COURSES")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COURSE_ID")
    private Long courseId;

    @Column(name="COURSE_NAME")
    private String courseName;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}
