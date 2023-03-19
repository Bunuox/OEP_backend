package com.iuc.cerrahpasa.onlineexamplatform.data.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="STUDENTS")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="STUDENT_ID")
	private Long studentId;

	@Column(name="IDENTIFICATION_NO")
	private Long identificationNo;

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

	@Column(name="IS_ACTIVE")
	private Boolean isActive;

	@Column(name="PASSWORD")
	private String password;
}
