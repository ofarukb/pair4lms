package com.tobeto.java4a.pair4lms.services.dtos.requests.authors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAuthorRequest {

    @NotBlank(message = "Yazar ismi boş bırakılamaz.")
    @Size(min = 2, max = 100, message = "Yazar ismi  2 ile 100 karakter arasında olmalıdır.")
    private String firstName;
    @NotBlank(message = "Yazar ismi boş bırakılamaz.")
    @Size(min = 2, max = 100, message = "Yazar soyismi 2 ile 100 karakter arasında olmalıdır.")
    private String lastName;
}
