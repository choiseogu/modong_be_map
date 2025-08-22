package com.my.modong_prac.service.reviewService;

import com.my.modong_prac.dto.reviewDto.ReviewRequestDto;
import com.my.modong_prac.dto.reviewDto.ReviewResponseDto;
import com.my.modong_prac.entity.ReviewEntity;
import com.my.modong_prac.entity.StoreEntity;
import com.my.modong_prac.entity.UserEntity;
import com.my.modong_prac.repository.ReviewRepository;
import com.my.modong_prac.repository.StoreRepository;
import com.my.modong_prac.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
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
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto reviewRequestDto) {
        UserEntity user = findUserById(reviewRequestDto.getUserId());
        StoreEntity store = findStoreById(reviewRequestDto.getStoreId());

        boolean reviewExists = reviewRepository.existsByUserIdAndStoreId(user, store);

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setUserId(user);
        reviewEntity.setStoreId(store);
        reviewEntity.setContent(reviewRequestDto.getReview());
        
        if (!reviewExists) {
            user.setUserStamp(user.getUserStamp() + 1);
            userRepository.save(user);
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
        UserEntity user = findUserById(userId);
        return reviewRepository.findByUserId(user);
    }

    @Override
    public List<ReviewEntity> getReviewByStoreId(String storeId) {
        StoreEntity store = findStoreById(storeId);
        return reviewRepository.findByStoreId(store);
    }

    @Override
    @Transactional
    public void deleteReviewByUserIdStoreId(String userId, String storeId) {
        UserEntity user = findUserById(userId);
        StoreEntity store = findStoreById(storeId);

        ReviewEntity review = reviewRepository.findByUserIdAndStoreId(user,store)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자의 매장에 해당하는 리뷰가 없습니다"));

        reviewRepository.delete(review);
    }

    @Override
    @Transactional
    public ReviewResponseDto updateReview(String userId, String storeId, ReviewRequestDto reviewRequestDto) {
        UserEntity user = findUserById(userId);
        StoreEntity store = findStoreById(storeId);

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

    private UserEntity findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private StoreEntity findStoreById(String storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
    }
}
