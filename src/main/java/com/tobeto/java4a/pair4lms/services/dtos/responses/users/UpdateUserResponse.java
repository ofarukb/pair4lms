package com.tobeto.java4a.pair4lms.services.dtos.responses.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
}
