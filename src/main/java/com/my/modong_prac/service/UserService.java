package com.my.modong_prac.service;

import com.my.modong_prac.dto.RequestDto;
import com.my.modong_prac.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(RequestDto requestDto);
    UserEntity updateUser(String id, RequestDto requestDto);
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(String id);
    void deleteUser(String id);
}
