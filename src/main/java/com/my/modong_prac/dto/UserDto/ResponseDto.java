package com.my.modong_prac.dto.UserDto;

import com.my.modong_prac.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    private String id;
    private String address;
//    private List<String> userPlace;
    private List<String> userMood;
    private Integer user_stamp;

    public ResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.address = userEntity.getAddress();
//        this.userPlace = userEntity.getUserPlace();
        this.userMood = userEntity.getUserMood();
        this.user_stamp = userEntity.getUserStamp();
    }
}
