package com.tobeto.java4a.pair4lms.services.dtos.responses.books;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListBookResponse {
    private int id;
    private String name;
    private int availableQuantity;
}
