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

    // review score average + review list
    // 요구 사항 :
    // average 에서 분모가 0일 경우, 즉 review 가 전혀 없을 경우, average 를 '0' 값으로 처리 한다.
    // average 소수점 아래 첫째자리까지 표현해야 한다.
    public List<StoreReviewResponseDto> saveOrderReviewScoreAverage(Long storeId) {

        // Map<Key 형, Value 형>
        Map<Long, List<ReviewResponseDto>> reviewResponseDtoMap =
                reviewRepository.findByStoreId(storeId)
                        .stream()
                        .map(ReviewResponseDto::of)
                        .collect(Collectors.groupingBy(ReviewResponseDto::getStoreId));

        return reviewResponseDtoMap.keySet().stream().map(it -> {
            Long reviewStoreId = it;
            double storeScoreAvg = 0;

            // average 를 실수로 표현하기 위해서 분자 또는 분모를 실수로 표현해야 해서 분자인 storeScoreSum 을 double 값으로 먼전 형변환 했다.
            double storeScoreSum = reviewResponseDtoMap.get(it).stream().map(ReviewResponseDto::getReviewScore).reduce((x, y) -> x + y).orElse(0);


            // 소수점 아래 둘째자리에서 반올림하여 결과값은 소수점 아래 첫째자리까지 표현한다.
            // 리뷰 없으면 size() 는 0 이기 때문에 예외 발생한다. 따라서 try ~ catch 사용했다.
            try {
                storeScoreAvg = Math.round((storeScoreSum / reviewResponseDtoMap.get(it).size()) * 10.0) / 10.0;
            } catch (Exception e) {
                storeScoreAvg = 0;
            }

            return StoreReviewResponseDto.builder().storeId(reviewStoreId).reviewScoreAvg(storeScoreAvg).reviewResponseDtoList(reviewResponseDtoMap.get(it)).build();
        }).collect(Collectors.toList());

    }
}
