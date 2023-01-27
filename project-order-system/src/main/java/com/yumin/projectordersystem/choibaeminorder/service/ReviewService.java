package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.domain.Review;
import com.yumin.projectordersystem.choibaeminorder.dto.ReviewRequestDto;
import com.yumin.projectordersystem.choibaeminorder.dto.ReviewResponseDto;
import com.yumin.projectordersystem.choibaeminorder.dto.ReviewUpdateRequestDto;
import com.yumin.projectordersystem.choibaeminorder.dto.StoreReviewResponseDto;
import com.yumin.projectordersystem.choibaeminorder.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // review 등록
    public void saveOrderReview(ReviewRequestDto reviewRequestDto) {

        reviewRepository.save(reviewRequestDto.toEntity());

    }

    // review score average + review list
    // 요구 사항 :
    // average 에서 분모가 0일 경우, 즉 review 가 전혀 없을 경우, average 를 '0' 값으로 처리 한다.
    // average 소수점 아래 첫째자리까지 표현해야 한다.
    @Transactional(readOnly = true)
    public StoreReviewResponseDto getStoreReview(Long storeId) {

        List<ReviewResponseDto> reviewResponseDtoList = reviewRepository
                .findByStoreId(storeId)
                .stream()
                .map(ReviewResponseDto::of)
                .collect(Collectors.toList());

        OptionalDouble wrapperStoreScoreAvg = getStoreReviewScoreAvg(reviewResponseDtoList);

        Double storeScoreAvg = wrapperStoreScoreAvg.isPresent() ? Math.round(wrapperStoreScoreAvg.getAsDouble() * 10.0) / 10.0 : 0.0;

        return StoreReviewResponseDto
                .builder()
                .storeId(storeId)
                .reviewScoreAvg(storeScoreAvg)
                .reviewResponseDtoList(reviewResponseDtoList)
                .build();

    }

    // 리뷰 점수 평균 계산 메서드
    public OptionalDouble getStoreReviewScoreAvg(List<ReviewResponseDto> reviewResponseDtoList) {
        return reviewResponseDtoList.stream().mapToInt(ReviewResponseDto::getReviewScore).average();
    }

    // review 수정 (dirty checking), ifPresent() 사용
    @Transactional(readOnly = false)
    public void modifyStoreReview(ReviewUpdateRequestDto reviewUpdateRequestDto) {

        reviewRepository.findById(reviewUpdateRequestDto.getReviewId()).ifPresent(it ->
                it.updateReview(reviewUpdateRequestDto.getReviewContent(), reviewUpdateRequestDto.getReviewScore()));

    }


    // [다른 방법] review 수정 (dirty checking), if 문 사용
//    @Transactional(readOnly = false)
//    public void modifyStoreReview(ReviewUpdateRequestDto reviewUpdateRequestDto) {
//        Optional<Review> wrapperModifyReview = reviewRepository.findById(reviewUpdateRequestDto.getReviewId());
//        Review modifyReview;
//
//        if (wrapperModifyReview.isPresent()) {
//            modifyReview = wrapperModifyReview.get();
//            modifyReview.updateReview(reviewUpdateRequestDto.getReviewContent(), reviewUpdateRequestDto.getReviewScore());
//        }
//    }



    // review 삭제
    public void deleteStoreReview(Long reviewId) {

        Optional<Review> wrapperReview = reviewRepository.findById(reviewId);

        if(wrapperReview.isPresent()) {
            reviewRepository.delete(wrapperReview.get());
        }
    }
}
