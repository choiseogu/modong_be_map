package com.my.modong_prac.service.reviewService;

import com.my.modong_prac.dto.ReviewDto.ReviewRequestDto;
import com.my.modong_prac.dto.ReviewDto.ReviewResponseDto;
import com.my.modong_prac.entity.ReviewEntity;
import com.my.modong_prac.entity.StoreEntity;
import com.my.modong_prac.entity.UserEntity;
import com.my.modong_prac.repository.ReviewRepository;
import com.my.modong_prac.repository.StoreRepository;
import com.my.modong_prac.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto) {
        UserEntity userId = userRepository.findById(reviewRequestDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        StoreEntity storeId = storeRepository.findById(reviewRequestDto.getStoreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "store not found"));

        boolean reviewExists = reviewRepository.existsByUserIdAndStoreId(userId, storeId);

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setUserId(userId);
        reviewEntity.setStoreId(storeId);
        reviewEntity.setContent(reviewRequestDto.getReview());
        if (!reviewExists) {
            userId.setUserStamp(userId.getUserStamp() + 1);
        }
        reviewRepository.save(reviewEntity);

        return new ReviewResponseDto(reviewEntity);
    }

    @Override
    public List<ReviewEntity> getAllReview() {
        List<ReviewEntity> reviewEntities = reviewRepository.findAll();
        if (reviewEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "등록된 리뷰가 없음");
        }
        return reviewEntities;
    }

    @Override
    public List<ReviewEntity> getReviewByUserId(String userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return reviewRepository.findByUserId(user);
    }

    @Override
    public List<ReviewEntity> getReviewByStoreId(String storeId) {
        StoreEntity store = storeRepository.findById(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
        return reviewRepository.findByStoreId(store);
    }

    @Override
    public void deleteReviewByUserIdStoreId(String userId, String storeId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        StoreEntity store = storeRepository.findById(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));

        ReviewEntity review = reviewRepository.findByUserIdAndStoreId(user,store)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자의 매장에 해당하는 리뷰가 없습니다"));

        reviewRepository.delete(review);
    }

    @Override
    public ReviewResponseDto updateReview(String userId, String storeId, ReviewRequestDto reviewRequestDto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        StoreEntity store = storeRepository.findById(storeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));

        if (!(reviewRepository.existsByUserIdAndStoreId(user, store))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자id와 매장id 필요");
        }

        return reviewRepository.findByUserIdAndStoreId(user, store)
                .map(reviewEntity -> {
                    reviewEntity.setContent(reviewRequestDto.getReview());
                    reviewRepository.save(reviewEntity);
                    return new ReviewResponseDto(reviewEntity);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"사용자의 매장에 해당하는 리뷰가 없습니다."));
    }
}
