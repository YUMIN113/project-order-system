package com.yumin.projectordersystem.choibaeminorder.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Builder
    public Review(Long storeId, String reviewContent, Integer reviewScore) {
        this.storeId = storeId;
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
    }

    // review 수정 메서드 (dirty checking)
    public void updateReview(String reviewContent, Integer reviewScore) {
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
    }
}
