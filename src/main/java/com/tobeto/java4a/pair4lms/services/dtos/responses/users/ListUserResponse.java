package com.tobeto.java4a.pair4lms.services.dtos.responses.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
