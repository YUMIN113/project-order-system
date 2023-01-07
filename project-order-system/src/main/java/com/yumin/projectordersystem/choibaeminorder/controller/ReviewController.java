package com.yumin.projectordersystem.choibaeminorder.controller;

import com.yumin.projectordersystem.choibaeminorder.dto.ReviewRequestDto;
import com.yumin.projectordersystem.choibaeminorder.dto.ReviewUpdateRequestDto;
import com.yumin.projectordersystem.choibaeminorder.dto.StoreReviewResponseDto;
import com.yumin.projectordersystem.choibaeminorder.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review/")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // review 등록
    @PostMapping("add")
    public ResponseEntity<String> enrollOrderReview(@RequestBody ReviewRequestDto reviewRequestDto) {

        reviewService.saveOrderReview(reviewRequestDto);

        return ResponseEntity.ok("ok");
    }

    // review 목록 + 평균 점수
    @GetMapping("store-review-list/{storeId}")
    public ResponseEntity<StoreReviewResponseDto> findStoreReviewList(@PathVariable(value = "storeId") Long storeId) {
        return ResponseEntity.ok(reviewService.getStoreReview(storeId));
    }

    // review 수정 (dirty checking)
    @PostMapping("modify")
    public ResponseEntity<String> modifyReview(@RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto) {
        reviewService.modifyStoreReview(reviewUpdateRequestDto);
        return ResponseEntity.ok("ok");
    }

    // review 삭제
    @GetMapping("delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "reviewId") Long reviewId) {
        reviewService.deleteStoreReview(reviewId);
        return ResponseEntity.ok("ok");
    }

 }
