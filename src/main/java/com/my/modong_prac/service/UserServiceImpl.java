package com.my.modong_prac.service;

import com.my.modong_prac.dto.RequestDto;
import com.my.modong_prac.entity.UserEntity;
import com.my.modong_prac.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(RequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id 입력은 필수");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId(requestDto.getId());
        userEntity.setAddress(requestDto.getAddress());
        userEntity.setUserPlace(requestDto.getUserPlace().lines().toList());
        userEntity.setUserMood(requestDto.getUserMood().lines().toList());

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(String id, RequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id 입력은 필수");
        }

        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntity.setId(requestDto.getId());
                    userEntity.setAddress(requestDto.getAddress());
                    if (requestDto.getUserPlace() != null) {
                        List<String> updatePlace = new ArrayList<>(
                                requestDto.getUserPlace().lines().toList()
                        );
                        userEntity.setUserPlace(updatePlace);
                    }
                    if (requestDto.getUserMood() != null) {
                        List<String> updateMood = new ArrayList<>(
                                requestDto.getUserMood().lines().toList()
                        );
                        userEntity.setUserMood(updateMood);
                    }
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id가 없음"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        if (userEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "등록된 사용자가 없음");
        }

        return userEntities;
    }

    @Override
    public Optional<UserEntity> getUserById(String id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id는 등록되어 있지 않음")));
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 사용자가 없음");
        }

        userRepository.deleteById(id);
    }
}
