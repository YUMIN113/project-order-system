package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.dto.MenuResponseDto;
import com.yumin.projectordersystem.choibaeminorder.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // 점포 별 메뉴 목록 보여주기
    public List<MenuResponseDto> getStoreMenuList(Long storeId) {
        return menuRepository
                .findByStoreId(storeId)
                .stream()
                .map(MenuResponseDto::of)
                .collect(Collectors.toList());
    }
}
