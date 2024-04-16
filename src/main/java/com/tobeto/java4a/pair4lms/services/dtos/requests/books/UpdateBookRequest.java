package com.tobeto.java4a.pair4lms.services.dtos.requests.books;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {

    @Min(value = 1, message = "Geçerli bir ID numarası giriniz.")
    private int id;
    @NotBlank(message = "Kitap ismi boş bırakılamaz.")
    @Size(min = 2, max = 100, message = "Kitap ismi 2 ile 100 karakter arasında olmalıdır.")
    private int name;
    @Min(value = 0, message = "Mevcut kitap miktarı negatif değer alamaz.")
    private int availableQuantity;
    @Positive(message = "Envanter miktarı 0'dan küçük olamaz.")
    private int inventoryQuantity;
}
