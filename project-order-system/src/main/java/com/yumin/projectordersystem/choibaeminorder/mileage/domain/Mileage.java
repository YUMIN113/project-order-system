package com.yumin.projectordersystem.choibaeminorder.mileage.domain;

import com.yumin.projectordersystem.choibaeminorder.mileage.enums.MileageStatus;
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

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "mileage_score")
    private Integer mileageScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "mileage_status", nullable = false)
    private MileageStatus mileageStatus;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Builder
    public Mileage(Long orderId,
                   Long memberId,
                   Integer mileageScore,
                   MileageStatus mileageStatus) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.mileageScore = mileageScore;
        this.mileageStatus = mileageStatus;
    }

    // 마일리지 적립
    public static Mileage createSaveMileage(Long orderId, Long memberId, Integer mileageScore) {
        return Mileage.builder()
                .orderId(orderId)
                .memberId(memberId)
                .mileageScore(mileageScore)
                .mileageStatus(MileageStatus.SAVE)
                .build();
    }

    // 마일리지 취소 또는 사용 시, 상태 변경
    public void updateSaveMileage(MileageStatus mileageStatus) {
        this.mileageStatus = mileageStatus;
    }
}
