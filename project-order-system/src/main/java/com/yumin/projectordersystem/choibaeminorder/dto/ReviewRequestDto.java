package com.yumin.projectordersystem.choibaeminorder.dto;

import com.yumin.projectordersystem.choibaeminorder.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {

    private Long storeId;

    private String reviewContent;

    private Integer reviewScore;

    @Builder
    public ReviewRequestDto(Long storeId, String reviewContent, Integer reviewScore) {
        this.storeId = storeId;
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
    }

    // DTO -> Entity
    public Review toEntity() {
        return Review.builder()
                .storeId(this.storeId)
                .reviewContent(this.reviewContent)
                .reviewScore(this.reviewScore)
                .build();
    }
}
