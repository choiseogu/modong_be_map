package com.my.modong_prac.dto.JjimTitleDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JtRequestDto {
    @Schema(description = "사용자 id", example = "johndoe123")
    private String userId;

    @Schema(description = "찜 제목", example = "연남 분위기 카페 모음")
    private String title;
}
