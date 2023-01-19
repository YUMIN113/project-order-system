package com.yumin.projectordersystem.choibaeminorder.mileage.dto;

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
