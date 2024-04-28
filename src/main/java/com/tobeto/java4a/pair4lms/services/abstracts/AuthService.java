package com.tobeto.java4a.pair4lms.services.abstracts;

import com.tobeto.java4a.pair4lms.services.dtos.requests.login.LoginRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.register.RegisterRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.login.LoginResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.register.RegisterResponse;

public interface AuthService {
	RegisterResponse register(RegisterRequest request);
	LoginResponse login(LoginRequest request);
	String generateNewHashForPassword(String password);
}
