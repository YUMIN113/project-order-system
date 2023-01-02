package com.yumin.projectordersystem.choibaeminorder.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "review_content")
    private String reviewContent;

    @Column(name = "review_score")
    private Integer reviewScore;

    @Builder
    public Review(Long storeId, String reviewContent, Integer reviewScore) {
        this.storeId = storeId;
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
    }
}
