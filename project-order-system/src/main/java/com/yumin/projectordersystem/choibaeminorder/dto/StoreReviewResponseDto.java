package com.yumin.projectordersystem.choibaeminorder.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StoreReviewResponseDto {

    private Long storeId;

    private Double reviewScoreAvg;

    private List<ReviewResponseDto> reviewResponseDtoList;

    @Builder
    public StoreReviewResponseDto(Long storeId,
                                  Double reviewScoreAvg,
                                  List<ReviewResponseDto> reviewResponseDtoList) {
        this.storeId = storeId;
        this.reviewScoreAvg = reviewScoreAvg;
        this.reviewResponseDtoList = reviewResponseDtoList;
    }
}
