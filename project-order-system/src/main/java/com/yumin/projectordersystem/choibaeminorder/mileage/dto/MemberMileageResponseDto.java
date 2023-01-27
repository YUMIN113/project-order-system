package com.yumin.projectordersystem.choibaeminorder.mileage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberMileageResponseDto {

    private Integer totalMileage;

    private List<MileageResponseDto> mileageResponseDtoList;

    @Builder
    public MemberMileageResponseDto(Integer totalMileage,
                                    List<MileageResponseDto> mileageResponseDtoList) {
        this.totalMileage = totalMileage;
        this.mileageResponseDtoList = mileageResponseDtoList;
    }
}
