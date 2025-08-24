package com.my.modong_prac.controller;

import com.my.modong_prac.dto.userDto.UserRequestDto;
import com.my.modong_prac.dto.userDto.UserResponseDto;
import com.my.modong_prac.entity.UserEntity;
import com.my.modong_prac.service.userService.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "user_info", description = "회원 api")
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(originPatterns = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "유저 생성", description = "json형식의 requsestBody를 통한 유저 생성")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto requestDto) {
        UserEntity userEntity = userService.createUser(requestDto);
        return ResponseEntity.ok(new UserResponseDto(userEntity));
    }

    @GetMapping
    @Operation(summary = "유저 전체 조회", description = "json 데이터 리스트 return")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "해당 유저 조회", description = "json 데이터 return")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String id) {
        return userService.getUserById(id)
                .map(UserResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "해당 유저 수정", description = "해당 id 지정 후 데이터 수정")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @RequestBody @Valid UserRequestDto requestDto) {
        try{
            UserEntity userEntity = userService.updateUser(id, requestDto);
            return ResponseEntity.ok(new UserResponseDto(userEntity));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "해당 유저 삭제", description = "해당 id 지정 후 데이터 삭제")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
