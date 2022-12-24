package com.yumin.projectordersystem.choibaeminorder.controller;

import com.yumin.projectordersystem.choibaeminorder.dto.StoreResponseDto;
import com.yumin.projectordersystem.choibaeminorder.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<StoreResponseDto>> findAllStoreList() {
        return ResponseEntity.ok(storeService.getStoreList());
    }

}
