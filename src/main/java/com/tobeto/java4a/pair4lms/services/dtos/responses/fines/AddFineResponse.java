package com.tobeto.java4a.pair4lms.services.dtos.responses.fines;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFineResponse {
    private int id;
    private double dailyAmount;
    private LocalDate createdAt;
}
