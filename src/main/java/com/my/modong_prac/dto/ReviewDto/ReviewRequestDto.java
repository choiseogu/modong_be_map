package com.my.modong_prac.dto.ReviewDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewRequestDto {
    @Schema(description = "사용자 id", example = "johndoe123")
    private String userId;

    @Schema(description = "매장 id", example = "cafe1")
    private String storeId;

    @Schema(description = "리뷰", example = "음식이 맛있어요")
    private String review;
}
