package com.my.modong_prac.service.userService;

import com.my.modong_prac.dto.userDto.UserRequestDto;
import com.my.modong_prac.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(UserRequestDto requestDto);
    UserEntity updateUser(String id, UserRequestDto requestDto);
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(String id);
    void deleteUser(String id);
}
