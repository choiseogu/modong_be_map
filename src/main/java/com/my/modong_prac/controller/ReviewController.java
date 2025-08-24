package com.my.modong_prac.controller;

import com.my.modong_prac.dto.reviewDto.ReviewRequestDto;
import com.my.modong_prac.dto.reviewDto.ReviewResponseDto;
import com.my.modong_prac.service.reviewService.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "review_info", description = "리뷰 api")
@RestController
@RequestMapping("/api/v2")
@CrossOrigin(originPatterns = "*")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/creatReview")
    @Operation(summary = "리뷰 생성", description = "json형식의 requsestBody를 통한 리뷰 생성")
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        ReviewResponseDto reviewResponseDto = reviewService.createReview(reviewRequestDto);

        return ResponseEntity.ok(reviewResponseDto);
    }

    @PutMapping("/updateReview/{userId}/{storeId}")
    @Operation(summary = "사용자의 해당 가게 리뷰 수정", description = "사용자,가게 일치 시 수정")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable String userId, @PathVariable String storeId, @RequestBody ReviewRequestDto reviewRequestDto) {
        ReviewResponseDto reviewResponseDto = reviewService.updateReview(userId, storeId, reviewRequestDto);

        return ResponseEntity.ok(reviewResponseDto);
    }

    @GetMapping("/getAllReview")
    @Operation(summary = "리뷰 전체 조회", description = "json 데이터 리스트 return")
    public ResponseEntity<List<ReviewResponseDto>> getAllReview() {
        List<ReviewResponseDto> AllReviewEntities = reviewService.getAllReview()
                .stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(AllReviewEntities);
    }

    @GetMapping("/userReview/{userId}")
    @Operation(summary = "한 사용자가 작성한 모든 리뷰 조회", description = "json 데이터 리스트 return")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewByStore(@PathVariable String userId) {
        List<ReviewResponseDto> userReviewEntities = reviewService.getReviewByUserId(userId)
                .stream().map(ReviewResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userReviewEntities);
    }

    @GetMapping("/storeReview/{storeId}")
    @Operation(summary = "한 가게에 작성된 모든 리뷰 조회", description = "json 데이터 리스트 return")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewByStoreId(@PathVariable String storeId) {
        List<ReviewResponseDto> storeReviewEntities = reviewService.getReviewByStoreId(storeId)
                .stream().map(ReviewResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(storeReviewEntities);
    }

    @DeleteMapping("deleteReview/{userId}/{storeId}")
    @Operation(summary = "사용자의 해당 가게 리뷰 삭제", description = "사용자,가게 일치 시 삭제")
    public ResponseEntity<Void> deleteReview(@PathVariable String userId, @PathVariable String storeId){
        reviewService.deleteReviewByUserIdStoreId(userId, storeId);
        return ResponseEntity.ok().build();
    }


}
