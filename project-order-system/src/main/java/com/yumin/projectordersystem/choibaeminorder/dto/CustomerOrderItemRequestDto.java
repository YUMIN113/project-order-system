package com.yumin.projectordersystem.choibaeminorder.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerOrderItemRequestDto {

    private Long menuId;

    private Integer menuCnt;
}



