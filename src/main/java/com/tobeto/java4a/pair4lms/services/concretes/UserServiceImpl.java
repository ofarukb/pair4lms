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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public AddUserResponse add(AddUserRequest request) {
        User user = UserMapper.INSTANCE.userFromAddRequest(request);
        User savedUser = userRepository.save(user);

        return UserMapper.INSTANCE.addResponseFromUser(savedUser);
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {
        User user = UserMapper.INSTANCE.userFromUpdateRequest(request);
        User savedUser = userRepository.save(user);

        return UserMapper.INSTANCE.updateResponseFromUser(savedUser);
    }

    @Override
    public List<ListUserResponse> getAll() {
        List<User> userList = userRepository.findAll();
        List<ListUserResponse> response = new ArrayList<>();
        for (User user : userList) {
            response.add(UserMapper.INSTANCE.listResponseFromUser(user));
        }
        return response;
    }

    @Override
    public ListUserResponse getById(int id) {
        User user =  getByUserId(id);
        return UserMapper.INSTANCE.listResponseFromUser(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }


    public User getByUserId(int id){
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(id + " ID'sine sahip bir kullanıcı bulunamadı."));
    }
}

