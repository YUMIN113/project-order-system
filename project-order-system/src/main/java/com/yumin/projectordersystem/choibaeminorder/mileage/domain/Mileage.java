package com.yumin.projectordersystem.choibaeminorder.mileage.domain;

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
public class Mileage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mileage_id")
    private Long mileageId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "mileage_score")
    private Integer mileageScore;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Builder
    public Mileage(Long memberId, Integer mileageScore) {
        this.memberId = memberId;
        this.mileageScore = mileageScore;
    }

    // 신규 구매자 마일리지 최초 적립
    public static Mileage createSaveMileage(Long memberId, Integer mileageScore) {
        return Mileage.builder()
                .mileageScore(mileageScore)
                .memberId(memberId)
                .build();
    }

    // 기존 구매자 마일리지 적립
    public void updateSaveMileage(Integer mileageScore) {
       this.mileageScore = mileageScore;
    }
}
