package com.tobeto.java4a.pair4lms.services.dtos.responses.authors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListAuthorResponse {
    private int id;
    private String firstName;
    private String lastName;
}
