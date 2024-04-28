package com.tobeto.java4a.pair4lms.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tobeto.java4a.pair4lms.services.abstracts.AuthService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.login.LoginRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.register.RegisterRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.login.LoginResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.register.RegisterResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("login")
	public LoginResponse login(@RequestBody @Valid LoginRequest request) {
		return authService.login(request);
	}
	
	@PostMapping("register")
	public RegisterResponse register(@RequestBody @Valid RegisterRequest request) {
		return authService.register(request);
	}
}
