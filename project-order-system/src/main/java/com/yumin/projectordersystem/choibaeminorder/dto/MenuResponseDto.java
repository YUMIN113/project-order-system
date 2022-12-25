package com.yumin.projectordersystem.choibaeminorder.dto;

import com.yumin.projectordersystem.choibaeminorder.domain.Menu;
import com.yumin.projectordersystem.choibaeminorder.domain.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class MenuResponseDto {

    private Long menuId;

    private Store store;

    private String menuName;

    private Integer menuPrice;

    private LocalDateTime createAt;

    private LocalDateTime modifyAt;

    @Builder
    public MenuResponseDto(Long menuId,
                           Store store,
                           String menuName,
                           Integer menuPrice,
                           LocalDateTime createAt,
                           LocalDateTime modifyAt) {
        this.menuId = menuId;
        this.store = store;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
    }

    // 확인 필요
    public static MenuResponseDto of(Menu menu) {
        return MenuResponseDto.builder()
                .menuId(menu.getMenuId())
                .store(new Store())
                .menuName(menu.getMenuName())
                .menuPrice(menu.getMenuPrice())
                .createAt(menu.getCreateAt())
                .modifyAt(menu.getModifyAt())
                .build();
    }
}
