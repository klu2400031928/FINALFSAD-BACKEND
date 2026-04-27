package com.foodkind.backend.service;

import com.foodkind.backend.dto.UpdateUserRequest;
import com.foodkind.backend.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(com.foodkind.backend.dto.RegisterRequest request);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);
}
