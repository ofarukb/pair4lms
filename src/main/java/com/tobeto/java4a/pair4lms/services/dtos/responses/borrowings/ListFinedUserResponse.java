package com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListFinedUserResponse {
	private int userId;
    private String userFirstName;
    private String userLastName;
    private String userPhone;
    private double totalFineAmount;
}
