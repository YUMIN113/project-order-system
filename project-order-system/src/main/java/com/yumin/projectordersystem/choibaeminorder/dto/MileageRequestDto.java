package com.yumin.projectordersystem.choibaeminorder.dto;

import com.yumin.projectordersystem.choibaeminorder.domain.Mileage;
import lombok.Builder;

public class MileageRequestDto {

    private Long memberId;

    private Integer mileageScore;


    @Builder
    public MileageRequestDto(Long memberId, Integer mileageScore) {
        this.memberId = memberId;
        this.mileageScore = mileageScore;
    }

}
