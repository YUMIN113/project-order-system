package com.yumin.projectordersystem.choibaeminorder.controller;

import com.yumin.projectordersystem.choibaeminorder.dto.MenuResponseDto;
import com.yumin.projectordersystem.choibaeminorder.dto.StoreResponseDto;
import com.yumin.projectordersystem.choibaeminorder.service.MenuService;
import com.yumin.projectordersystem.choibaeminorder.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final MenuService menuService;


    public StoreController(StoreService storeService, MenuService menuService) {
        this.storeService = storeService;
        this.menuService = menuService;
    }

    // 모든 점포 목록 보여주기
    @GetMapping("/list")
    public ResponseEntity<List<StoreResponseDto>> findAllStoreList() {
        return ResponseEntity.ok(storeService.getStoreList());
    }

    // 점포 별 메뉴 목록 보여주기
    @GetMapping("/store-menu-list/{storeId}")
    public ResponseEntity<List<MenuResponseDto>> findStoreMenuList(@PathVariable(value = "storeId") Long storeId) {
        return ResponseEntity.ok(menuService.getStoreMenuList(storeId));
    }

}
