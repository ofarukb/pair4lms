package com.tobeto.java4a.pair4lms.services.concretes;

import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.java4a.pair4lms.entities.User;
import com.tobeto.java4a.pair4lms.repositories.UserRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.UserService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.AddUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.users.UpdateUserRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.AddUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.ListUserResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.users.UpdateUserResponse;
import com.tobeto.java4a.pair4lms.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public AddUserResponse add(AddUserRequest request) {
		userWithSameEmailShouldNotExist(request.getEmail());

		User user = UserMapper.INSTANCE.userFromAddRequest(request);
		User savedUser = userRepository.save(user);

		return UserMapper.INSTANCE.addResponseFromUser(savedUser);
	}

	@Override
	public UpdateUserResponse update(UpdateUserRequest request) {
		userWithSameEmailShouldNotExistWhenUpdating(request.getEmail(), request.getId());

		User user = UserMapper.INSTANCE.userFromUpdateRequest(request);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		User savedUser = userRepository.save(user);

		return UserMapper.INSTANCE.updateResponseFromUser(savedUser);
	}

	@Override
	public List<ListUserResponse> getAll() {
		List<User> userList = userRepository.findAll();
		return userList.stream().map(UserMapper.INSTANCE::listResponseFromUser).collect(Collectors.toList());
	}

	@Override
	public ListUserResponse getById(int id) {
		User user = getByUserId(id);
		return UserMapper.INSTANCE.listResponseFromUser(user);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public User getByUserId(int id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new BusinessException(id + " ID'sine sahip bir kullanıcı bulunamadı."));
	}

	private void userWithSameEmailShouldNotExist(String email) {
		userRepository.findByEmail(email).ifPresent((user) -> {
			throw new BusinessException("Kullanıcı zaten kayıtlı.");
		});
	}

	private void userWithSameEmailShouldNotExistWhenUpdating(String email, int userId) {
		User user = getByUserId(userId);
		Optional<User> userByEmail = userRepository.findByEmail(email);

		if (userByEmail.isPresent() && userByEmail.get().getId() != user.getId())
			throw new BusinessException("Kullanıcı zaten kayıtlı. Farklı bir e-posta adresi belirleyin.");
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElseThrow();
	}
}
