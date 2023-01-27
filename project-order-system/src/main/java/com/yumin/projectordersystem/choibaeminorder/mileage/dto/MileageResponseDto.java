package com.yumin.projectordersystem.choibaeminorder.mileage.dto;

import com.yumin.projectordersystem.choibaeminorder.mileage.domain.Mileage;
import com.yumin.projectordersystem.choibaeminorder.mileage.enums.MileageStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class MileageResponseDto {

    private Long memberId;

    private Long orderId;

    private Integer mileageScore;

    private MileageStatus mileageStatus;

    private LocalDateTime createAt;

    @Builder
    public MileageResponseDto(Long memberId,
                              Long orderId,
                              Integer mileageScore,
                              MileageStatus mileageStatus,
                              LocalDateTime createAt) {
        this.memberId = memberId;
        this.orderId = orderId;
        this.mileageScore = mileageScore;
        this.mileageStatus = mileageStatus;
        this.createAt = createAt;
    }

    public static MileageResponseDto of(Mileage mileage) {
        return MileageResponseDto.builder()
                .memberId(mileage.getMemberId())
                .orderId(mileage.getOrderId())
                .mileageScore(mileage.getMileageScore())
                .mileageStatus(mileage.getMileageStatus())
                .createAt(mileage.getCreateAt())
                .build();
    }
}
