package com.iuc.cerrahpasa.onlineexamplatform.data.payloads.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.iuc.cerrahpasa.onlineexamplatform.data.model.Gender;

import lombok.Data;

@Data
public class StudentRequest {

	@NotBlank
	@NotNull
	private Long identificationNo;

	@NotBlank
	@NotNull
	private String firstName;

	@NotBlank
	@NotNull
	private String lastName;

	@NotBlank
	@NotNull
	private String email;

	@NotBlank
	@NotNull
	private Gender gender;

	@NotBlank
	@NotNull
	private LocalDate dateOfBirth;

	@NotBlank
	@NotNull
	private Boolean isActive;

	@NotBlank
	@NotNull
	private String password;
}
