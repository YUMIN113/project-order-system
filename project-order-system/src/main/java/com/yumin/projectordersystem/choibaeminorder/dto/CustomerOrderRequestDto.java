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

    private List<CustomerOrderItemRequestDto> customerOrderItemRequestDtoList;
}
