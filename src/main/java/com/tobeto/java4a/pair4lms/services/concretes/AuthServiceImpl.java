package com.tobeto.java4a.pair4lms.services.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.java4a.pair4lms.entities.Role;
import com.tobeto.java4a.pair4lms.entities.User;
import com.tobeto.java4a.pair4lms.services.abstracts.AuthService;
import com.tobeto.java4a.pair4lms.services.abstracts.UserService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.login.LoginRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.register.RegisterRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.login.LoginResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.register.RegisterResponse;
import com.tobeto.java4a.pair4lms.services.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public RegisterResponse register(RegisterRequest request) {
		passwordsMustBeSame(request.getPassword(), request.getRepassword());

		User newUser = UserMapper.INSTANCE.userFromRegisterRequest(request);
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.USER);
		newUser.setAuthorities(roles);
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
		User savedUser = userService.save(newUser);

		return UserMapper.INSTANCE.registerResponseFromUser(savedUser);
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		User user = userService.getByEmail(request.getEmail())
				.orElseThrow(() -> new BusinessException("Kullanıcı bulunamadı ya da şifre yanlış"));

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		if (!authentication.isAuthenticated())
			throw new BusinessException("Kullanıcı bulunamadı ya da şifre yanlış");

		return UserMapper.INSTANCE.loginResponseFromUser(user);
	}

	@Override
	public String generateNewHashForPassword(String password) {
		return passwordEncoder.encode(password);
	}

	private void passwordsMustBeSame(String password, String repassword) {
		if (!password.equals(repassword))
			throw new BusinessException("Şifreler birbiriyle uyuşmuyor.");
	}
}
