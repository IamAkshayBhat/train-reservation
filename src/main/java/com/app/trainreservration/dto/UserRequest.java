package com.app.trainreservration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotBlank(message = "firstname must not be blank")
	@Pattern(
	        regexp = "^[A-Za-z]{2,50}$",
	        message = "firstname must contain only letters without spaces (2 to 50 characters)")
	private String firstName;
	
	@NotBlank(message = "lastname must not be blank")
	@Pattern(
	        regexp = "^[A-Za-z]{2,50}$",
	        message = "lastname must contain only letters without spaces (2 to 50 characters)")
	private String lastName;
	
	@NotBlank(message = "email must not be blank")
	@Email(message = "email should be valid")
	private String email;
	
}
