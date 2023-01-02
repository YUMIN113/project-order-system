package com.yumin.projectordersystem.choibaeminorder.dto;


import com.yumin.projectordersystem.choibaeminorder.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponseDto {

    private Long reviewId;

    private Long storeId;

    private String reviewContent;

    private Integer reviewScore;

    private LocalDateTime createAt;

    private LocalDateTime modifyAt;

    @Builder
    public ReviewResponseDto(Long reviewId,
                             Long storeId,
                             String reviewContent,
                             Integer reviewScore,
                             LocalDateTime createAt,
                             LocalDateTime modifyAt) {
        this.reviewId = reviewId;
        this.storeId = storeId;
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
    }

    public static ReviewResponseDto of(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getReviewId())
                .storeId(review.getStoreId())
                .reviewContent(review.getReviewContent())
                .reviewScore(review.getReviewScore())
                .createAt(review.getCreateAt())
                .modifyAt(review.getModifyAt())
                .build();
    }
}
