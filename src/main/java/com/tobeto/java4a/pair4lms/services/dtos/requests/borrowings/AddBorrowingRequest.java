package com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBorrowingRequest {
    @Positive(message = "Kitap ID'si 0'dan küçük olamaz.")
    private int bookId;
    @Positive(message = "Kullanıcı ID'si 0'dan küçük olamaz.")
    private int userId;
}
