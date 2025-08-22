package com.my.modong_prac.dto.userDto;

import com.my.modong_prac.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private String id;
    private String address;
    private List<String> userMood;
    private Integer user_stamp;

    public UserResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.address = userEntity.getAddress();
        this.userMood = userEntity.getUserMood();
        this.user_stamp = userEntity.getUserStamp();
    }
}