package com.tobeto.java4a.pair4lms.services.dtos.requests.fines;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFineRequest {
    @Positive(message = "Günlük ceza bedeli negatif değer alamaz.")
    private double dailyAmount;
}
