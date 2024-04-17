package com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBookResponse {
    private int id;
    private int bookId;
    private String bookName;
    private int userId;
    private String userFirstName;
    private String userLastName;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double totalFineAmount;
}
