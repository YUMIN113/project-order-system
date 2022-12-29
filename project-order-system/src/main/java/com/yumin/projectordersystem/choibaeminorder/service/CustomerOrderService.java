package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.domain.Menu;
import com.yumin.projectordersystem.choibaeminorder.domain.CustomerOrder;
import com.yumin.projectordersystem.choibaeminorder.domain.Store;
import com.yumin.projectordersystem.choibaeminorder.dto.CustomerOrderRequestDto;
import com.yumin.projectordersystem.choibaeminorder.enums.OrderStatus;
import com.yumin.projectordersystem.choibaeminorder.repository.MenuRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.CustomerOrderRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerOrderService {

  private final CustomerOrderRepository customerOrderRepository;

    public CustomerOrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    //
//    public void Order(Long menuId, Integer orderCnt) {
//
//       Optional<Menu> menuWrapper = menuRepository.findById(menuId);
//       Menu menu = menuWrapper.get();
//       Long storeId = menu.getStoreId();
//
//       Optional<Store> storeWrapper = storeRepository.findByStoreId(storeId);
//       Store store = storeWrapper.get();
//
//       Integer totalPrice = menu.getMenuPrice() * orderCnt;
//
//       CustomerOrder saveOrder = CustomerOrder.builder()
//               .store(store)
//               .orderStatus(OrderStatus.COMPLETION)
//               .totalPrice(totalPrice)
//               .build();
//
//       orderRepository.save(saveOrder);
//
//    }

    public CustomerOrder enrollCustomerOrder(Long storeId, Integer totalPrice) {


        // customer_order DB 에 저장
        return customerOrderRepository.save(CustomerOrder.createCustomerOrder(storeId, totalPrice));




    }
}
