package com.tobeto.java4a.pair4lms.services.mappers;

import com.tobeto.java4a.pair4lms.entities.User;
import com.tobeto.java4a.pair4lms.services.dtos.requests.register.RegisterRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.AddUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.UpdateUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.login.LoginResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.register.RegisterResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.AddUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.ListUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.UpdateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "borrowings", ignore = true)
	User userFromAddRequest(AddUserRequest request);

	AddUserResponse addResponseFromUser(User user);

	@Mapping(target = "borrowings", ignore = true)
	User userFromUpdateRequest(UpdateUserRequest request);

	UpdateUserResponse updateResponseFromUser(User user);

	ListUserResponse listResponseFromUser(User user);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "borrowings", ignore = true)
	User userFromRegisterRequest(RegisterRequest request);

	RegisterResponse registerResponseFromUser(User user);

	LoginResponse loginResponseFromUser(User user);
}
