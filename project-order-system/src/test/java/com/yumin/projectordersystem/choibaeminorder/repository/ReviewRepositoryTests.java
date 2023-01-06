package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.dto.ReviewResponseDto;
import com.yumin.projectordersystem.choibaeminorder.dto.StoreReviewResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    // groupingBy + averagingDouble 사용하여 평균 구하기
    @Test
    public void getReviewAvgTest1() {

        Map<Long, Double> reviewAvg = reviewRepository.findByStoreId(1L)
                .stream()
                .map(ReviewResponseDto::of)
                .collect(Collectors.groupingBy(ReviewResponseDto::getStoreId, averagingDouble(ReviewResponseDto::getReviewScore)));

        log.info("average = " + reviewAvg.toString());

    }

    // reduce 와 '/' 사용하여 평균 구하기
    @Test
    public void getReviewAvgTest2() {
        List<ReviewResponseDto> reviewResponseDtoList =
                reviewRepository.findByStoreId(3L)
                        .stream()
                        .map(ReviewResponseDto::of)
                        .collect(Collectors.toList());

        double reviewScoreSum = Double.valueOf(reviewResponseDtoList.stream().map(it -> {
            return it.getReviewScore();
        }).reduce((x, y) -> x + y).orElse(0));

        // 소수점 아래 둘째자리에서 반올림하여 결과값은 소수점 아래 첫째자리까지 표현한다.
        // 리뷰 없으면 size() 는 0 이기 때문에 예외 발생한다. 따라서 try ~ catch 사용했다.

        double reviewScoreAvg = 0;
        try {
            reviewScoreAvg = Math.round((reviewScoreSum / reviewResponseDtoList.size()) * 10.0) / 10.0;
        } catch (Exception e) {
            return;
        }

        // Test 값 출력 부분
        int length = reviewResponseDtoList.size();

        reviewResponseDtoList.forEach(it -> log.info(it.toString()));
        log.info("sum = " + reviewScoreSum);
        log.info("avg = " +reviewScoreAvg);
        log.info("length = " + length);

    }
}
