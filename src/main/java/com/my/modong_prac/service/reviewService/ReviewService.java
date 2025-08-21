package com.my.modong_prac.service.reviewService;

import com.my.modong_prac.dto.ReviewDto.ReviewRequestDto;
import com.my.modong_prac.dto.ReviewDto.ReviewResponseDto;
import com.my.modong_prac.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto);
    ReviewResponseDto updateReview(String userId, String storeId, ReviewRequestDto reviewRequestDto);
    List<ReviewEntity> getAllReview();
    List<ReviewEntity> getReviewByUserId(String userId);
    List<ReviewEntity> getReviewByStoreId(String storeId);
    void deleteReviewByUserIdStoreId(String userId, String storeId);
}
