package com.tobeto.java4a.pair4lms.services.dtos.requests.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	@NotBlank(message = "İsim boş bırakılamaz.")
	@Size(min = 2, max = 100, message = "İsim 2 ile 100 karakter arasında olmalıdır.")
	private String firstName;

	@NotBlank(message = "Soyisim boş bırakılamaz.")
	@Size(min = 2, max = 100, message = "Soyisim 2 ile 100 karakter arasında olmalıdır.")
	private String lastName;

	@NotBlank(message = "Telefon numarası boş bırakılamaz.")
	@Pattern(regexp = "^$|[0-9]{10}", message = "Geçersiz telefon numarası formatı. Lütfen 10 haneli bir numara giriniz.")
	private String phone;

	@Email(message = "Lütfen geçerli bir e-posta adresi giriniz")
	private String email;

	@NotBlank(message = "Şifre boş bırakılamaz")
	@Size(min = 6, message = "Şifre en az 6 karakterden oluşmalıdır")
	private String password;
	
	@NotBlank(message = "Şifre boş bırakılamaz")
	@Size(min = 6, message = "Şifre en az 6 karakterden oluşmalıdır")
	private String repassword;
}
