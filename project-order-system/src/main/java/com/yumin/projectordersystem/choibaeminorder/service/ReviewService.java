package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.dto.ReviewRequestDto;
import com.yumin.projectordersystem.choibaeminorder.dto.ReviewResponseDto;
import com.yumin.projectordersystem.choibaeminorder.dto.StoreReviewResponseDto;
import com.yumin.projectordersystem.choibaeminorder.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    // review store average + review list
    // average 에서 분모가 0일 경우, 즉 review 가 전혀 없을 경우 처리 부분 고민해야 한다.
    public List<StoreReviewResponseDto> saveOrderReviewScoreAverage(Long storeId) {

        Map<Long, List<ReviewResponseDto>> reviewResponseDtoMap =
                reviewRepository.findByStoreId(storeId)
                                .stream()
                                .map(ReviewResponseDto::of)
                                .collect(Collectors.groupingBy(ReviewResponseDto::getStoreId));

        return reviewResponseDtoMap.keySet().stream().map(it -> {
            Long reviewStoreId = it;
            Integer storeScoreSum = reviewResponseDtoMap.get(it).stream().map(ReviewResponseDto::getReviewScore).reduce((x ,y) -> x + y).get();
            double storeScoreAvg = storeScoreSum / reviewResponseDtoMap.get(it).size();
            return StoreReviewResponseDto.builder().storeId(reviewStoreId).reviewScoreAvg(storeScoreAvg).reviewResponseDtoList(reviewResponseDtoMap.get(it)).build();
        }).collect(Collectors.toList());
    }

}
