package com.tobeto.java4a.pair4lms.services.abstracts;

import com.tobeto.java4a.pair4lms.entities.User;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.AddUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.UpdateUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.AddUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.ListUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.UpdateUserResponse;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AddUserResponse add(AddUserRequest request);
    UpdateUserResponse update(UpdateUserRequest request);
    List<ListUserResponse> getAll();
    ListUserResponse getById(int id);
    void delete(int id);
    User getByUserId(int id);
    User save(User user);
    Optional<User> getByEmail(String email);
}
