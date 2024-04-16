package com.tobeto.java4a.pair4lms.services.dtos.requests.users;

import jakarta.validation.constraints.Min;
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
public class UpdateUserRequest {

    @Min(value = 1, message = "Geçerli bir ID numarası giriniz.")
    private int id;

    @NotBlank(message = "İsim boş bırakılamaz.")
    @Size(min = 2, max = 100, message = "İsim 2 ile 100 karakter arasında olmalıdır.")
    private String firstName;

    @NotBlank(message = "Soyisim boş bırakılamaz.")
    @Size(min = 2, max = 100, message = "Soyisim 2 ile 100 karakter arasında olmalıdır.")
    private String lastName;

    @NotBlank(message = "Telefon numarası boş bırakılamaz.")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Geçersiz telefon numarası formatı. Lütfen 10 haneli bir numara giriniz.")
    private String phone;
}