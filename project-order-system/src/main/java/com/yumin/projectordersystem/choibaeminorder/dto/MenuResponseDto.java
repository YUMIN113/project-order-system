package com.yumin.projectordersystem.choibaeminorder.dto;

import com.yumin.projectordersystem.choibaeminorder.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class MenuResponseDto {

    private Long menuId;

    private Long storeId;

    private String menuName;

    private Integer menuPrice;

    private LocalDateTime createAt;

    private LocalDateTime modifyAt;

    @Builder
    public MenuResponseDto(Long menuId,
                           Long storeId,
                           String menuName,
                           Integer menuPrice,
                           LocalDateTime createAt,
                           LocalDateTime modifyAt) {
        this.menuId = menuId;
        this.storeId = storeId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
    }

    public static MenuResponseDto of(Menu menu) {
        return MenuResponseDto.builder()
                .menuId(menu.getMenuId())
                .storeId(menu.getStoreId())
                .menuName(menu.getMenuName())
                .menuPrice(menu.getMenuPrice())
                .createAt(menu.getCreateAt())
                .modifyAt(menu.getModifyAt())
                .build();
    }
}
