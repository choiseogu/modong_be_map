package com.my.modong_prac.dto.ReviewDto;

import com.my.modong_prac.entity.ReviewEntity;
import lombok.Data;

@Data
public class ReviewResponseDto {
    private String userId;
    private String storeId;
    private String review;

    public ReviewResponseDto(ReviewEntity reviewEntity) {
        this.userId = reviewEntity.getUserId().getId();
        this.storeId = reviewEntity.getStoreId().getStoreName();
        this.review = reviewEntity.getContent();
    }
}
