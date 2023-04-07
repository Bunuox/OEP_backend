package com.iuc.cerrahpasa.onlineexamplatform.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="INSTRUCTORS")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="INSTRUCTOR_ID")
    private Long instructorId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL")
    private String email;

    @Column(name="GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name="DEPARTMENT")
    private String department;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="IS_ACTIVE")
    private Boolean isActive;
}
