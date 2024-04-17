package com.tobeto.java4a.pair4lms.services.mappers;

import com.tobeto.java4a.pair4lms.entities.User;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.AddUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.UpdateUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.AddUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.ListUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.UpdateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userFromAddRequest(AddUserRequest request);

    AddUserResponse addResponseFromUser(User user);

    User userFromUpdateRequest(UpdateUserRequest request);

    UpdateUserResponse updateResponseFromUser(User user);

    ListUserResponse listResponseFromUser(User user);

}
