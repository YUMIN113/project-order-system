package com.yumin.projectordersystem.choibaeminorder.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerOrderRequestDto {

    private Long storeId;

    // Test 위해 생성
    private Long memberId;


    private List<CustomerOrderItemRequestDto> customerOrderItemRequestDtoList;
}
