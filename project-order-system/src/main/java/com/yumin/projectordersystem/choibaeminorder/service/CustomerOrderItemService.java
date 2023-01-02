package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.domain.CustomerOrderItem;
import com.yumin.projectordersystem.choibaeminorder.dto.CustomerOrderItemRequestDto;
import com.yumin.projectordersystem.choibaeminorder.repository.CustomerOrderItemRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class CustomerOrderItemService {

    private final MenuRepository menuRepository;

    private final CustomerOrderItemRepository customerOrderItemRepository;

    public CustomerOrderItemService(MenuRepository menuRepository, CustomerOrderItemRepository customerOrderItemRepository) {
        this.menuRepository = menuRepository;
        this.customerOrderItemRepository = customerOrderItemRepository;
    }

    // CustomerOrder totalPrice 변수 값을 구하기 위한 메서드
    public Integer getCustomerOrderItemListTotalPrice(List<CustomerOrderItemRequestDto> customerOrderItemRequestDtoList) {

        return customerOrderItemRequestDtoList.stream().map(it -> {
            return menuRepository.findById(it.getMenuId()).get().getMenuPrice() * it.getMenuCnt();
        }).reduce((x, y) -> x + y).get();

        // Optional 객체에 저장된 값에 접근하기 위해서 get() 메서드 사용해야 한다.
    }



//    다른 방법 : 향상된 for 문으로 totalPrice 구하기
//    public Integer getCustomerOrderItemListTotalPrice(List<CustomerOrderItemRequestDto> customerOrderItemRequestDtoList) {
//
//        Integer totalPrice = 0;
//        for (CustomerOrderItemRequestDto orderMenu : customerOrderItemRequestDtoList) {
//
//            Optional<Menu> menuWrapper = menuRepository.findById(orderMenu.getMenuId());
//            totalPrice =+ menuWrapper.get().getMenuPrice() * orderMenu.getMenuCnt();
//        }
//        return totalPrice;
//    }



    // 주문 상품 정보 생성 메서드
    public void saveCustomerOrderItemList(Long orderId, List<CustomerOrderItemRequestDto> customerOrderItemRequestDtoList) {

        List<CustomerOrderItem> saveCustomerOrderItem = customerOrderItemRequestDtoList
                .stream()
                .map(it -> CustomerOrderItem.createEnrollCustomerOrderItem(orderId, it))
                .collect(Collectors.toList());

        customerOrderItemRepository.saveAll(saveCustomerOrderItem);

    }
}
