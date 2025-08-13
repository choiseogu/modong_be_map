package com.my.modong_prac.controller;

import com.my.modong_prac.dto.RequestDto;
import com.my.modong_prac.dto.ResponseDto;
import com.my.modong_prac.entity.UserEntity;
import com.my.modong_prac.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "user_info", description = "회원 api")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid RequestDto requestDto) {
        UserEntity userEntity = userService.createUser(requestDto);
        return ResponseEntity.ok(new ResponseDto(userEntity));
    }

    @GetMapping
    public List<ResponseDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable String id, @RequestBody @Valid RequestDto requestDto) {
        try{
            UserEntity userEntity = userService.updateUser(id, requestDto);
            return ResponseEntity.ok(new ResponseDto(userEntity));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
