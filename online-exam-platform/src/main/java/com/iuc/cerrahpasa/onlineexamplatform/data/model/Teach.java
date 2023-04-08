package com.iuc.cerrahpasa.onlineexamplatform.data.model;

import com.iuc.cerrahpasa.onlineexamplatform.data.CompositeKeys.TeachCompositeKeys;
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
@Table(name="TEACH")
@IdClass(TeachCompositeKeys.class)
public class Teach {

    @Id
    @Column(name = "INSTRUCTOR_ID")
    private Long instructorId;

    @Id
    @Column(name = "COURSE_ID")
    private Long courseId;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}
