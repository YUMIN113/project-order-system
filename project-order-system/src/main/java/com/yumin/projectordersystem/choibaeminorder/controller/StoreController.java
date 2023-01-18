package com.yumin.projectordersystem.choibaeminorder.controller;

import com.yumin.projectordersystem.choibaeminorder.domain.CustomerOrder;
import com.yumin.projectordersystem.choibaeminorder.dto.CustomerOrderRequestDto;
import com.yumin.projectordersystem.choibaeminorder.dto.MenuResponseDto;
import com.yumin.projectordersystem.choibaeminorder.dto.StoreResponseDto;
import com.yumin.projectordersystem.choibaeminorder.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final MenuService menuService;

    private final CustomerOrderItemService customerOrderItemService;

    private final CustomerOrderService customerOrderService;

    private final MileageService mileageService;


    public StoreController(StoreService storeService, MenuService menuService, CustomerOrderItemService customerOrderItemService, CustomerOrderService customerOrderService, MileageService mileageService) {
        this.storeService = storeService;
        this.menuService = menuService;
        this.customerOrderItemService = customerOrderItemService;
        this.customerOrderService = customerOrderService;
        this.mileageService = mileageService;
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

    @PostMapping("/store-order")
    public ResponseEntity<String> enrollStoreOrder(@RequestBody CustomerOrderRequestDto customerOrderRequestDto) {

        // 주문 총액 구하기
        Integer totalPrice = customerOrderItemService.getCustomerOrderItemListTotalPrice(customerOrderRequestDto.getCustomerOrderItemRequestDtoList());

        // 주문 정보 DB 저장
        CustomerOrder saveCustomerOrder
                = customerOrderService.enrollCustomerOrder(customerOrderRequestDto.getStoreId(), customerOrderRequestDto.getMemberId(), totalPrice);

        // 주문 상품 정보 DB 저장
        customerOrderItemService.saveCustomerOrderItemList(saveCustomerOrder.getOrderId(), customerOrderRequestDto.getCustomerOrderItemRequestDtoList());

        // 마일리지 적립
        mileageService.saveMileage(customerOrderRequestDto.getMemberId(), totalPrice);

        return ResponseEntity.ok("ok");
    }

}
