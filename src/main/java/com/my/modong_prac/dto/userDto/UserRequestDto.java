package com.my.modong_prac.dto.userDto;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class UserRequestDto {

    @Schema(description = "사용자 id", example = "johndoe123")
    private String id;

    @Schema(description = "사용자 거주 지역", example = "정릉")
    private String address;

//    @Schema(description = "사용자 최애 장소", example ="홍대\n연남\n신촌")
//    private String userPlace;

    @Schema(description = "사용자 선호 분위기", example = "조용한\n독서하기 좋은\n안락한")
    private List<String> userMood;
    
    // 문자열로 받을 경우 자동으로 List로 변환하는 커스텀 setter
    @JsonSetter("userMood")
    public void setUserMoodFromString(Object userMood) {
        if (userMood instanceof String) {
            String moodString = (String) userMood;
            if (moodString.trim().isEmpty()) {
                this.userMood = Arrays.asList();
            } else {
                // 개행문자(\n) 또는 쉼표로 구분된 문자열을 List로 변환
                this.userMood = Arrays.stream(moodString.split("[,\n]"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();
            }
        } else if (userMood instanceof List) {
            this.userMood = (List<String>) userMood;
        }
    }
}
