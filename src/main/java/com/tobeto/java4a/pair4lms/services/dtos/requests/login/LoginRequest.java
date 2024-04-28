package com.tobeto.java4a.pair4lms.services.dtos.requests.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	@Email(message = "Lütfen geçerli bir e-posta adresi giriniz")
	private String email;
	@NotBlank
	private String password;
}
