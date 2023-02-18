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
        }).reduce((x, y) -> x + y).orElse(0);

    }

//         return customerOrderItemRequestDtoList.stream().map(it -> {
//             return menuRepository.findById(it.getMenuId()).get().getMenuPrice() * it.getMenuCnt();
//         }).reduce(0, (x, y) -> x + y);

    // Optional 객체에 들어있는 값에 접근하기 위해서 get() 사용할 수 있다. 하지만 해당 값이 null 일 경우, get()을 통해 호출하면 예외 발생 한다.
    // 따라서 orElse() 사용했다. orElse() 통해서 해당 값이 null 일 경우 예외를 발생시키지 않고, 어떤 결과값으로 반환할 지 지정할 수 있다.
    // reduce() 에서 identity 값 지정한다면 Optional 이 아닌 Integer 로 결과값 도출할 수 있다.



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
