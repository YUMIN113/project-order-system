package com.yumin.projectordersystem.choibaeminorder.mileage.dto;

import com.yumin.projectordersystem.choibaeminorder.dto.CustomerOrderItemRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MileageSubtractRequestDto {

    private Long memberId;

    private List<CustomerOrderItemRequestDto> customerOrderItemRequestDtoList;

}
