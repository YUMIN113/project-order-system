package com.yumin.projectordersystem.choibaeminorder.controller;

import com.yumin.projectordersystem.choibaeminorder.dto.MenuResponseDto;
import com.yumin.projectordersystem.choibaeminorder.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

}
