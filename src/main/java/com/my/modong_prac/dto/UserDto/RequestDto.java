package com.my.modong_prac.dto.UserDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestDto {

    @Schema(description = "사용자 id", example = "johndoe123")
    private String id;

    @Schema(description = "사용자 거주 지역", example = "정릉")
    private String address;

//    @Schema(description = "사용자 최애 장소", example ="홍대\n연남\n신촌")
//    private String userPlace;

    @Schema(description = "사용자 선호 분위기", example = "조용한\n독서하기 좋은\n안락한")
    private String userMood;
}
