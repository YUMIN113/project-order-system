package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.domain.Menu;
import com.yumin.projectordersystem.choibaeminorder.domain.Order;
import com.yumin.projectordersystem.choibaeminorder.domain.Store;
import com.yumin.projectordersystem.choibaeminorder.enums.OrderStatus;
import com.yumin.projectordersystem.choibaeminorder.repository.MenuRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.OrderRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final MenuRepository menuRepository;

    private final StoreRepository storeRepository;

    private final OrderRepository orderRepository;


    public OrderService(MenuRepository menuRepository, StoreRepository storeRepository, OrderRepository orderRepository) {
        this.menuRepository = menuRepository;
        this.storeRepository = storeRepository;
        this.orderRepository = orderRepository;
    }

    public void Order(Long menuId, Integer orderCnt) {

       Optional<Menu> menuWrapper = menuRepository.findById(menuId);
       Menu menu = menuWrapper.get();
       Long storeId = menu.getStoreId();

       Optional<Store> storeWrapper = storeRepository.findByStoreId(storeId);
       Store store = storeWrapper.get();

       Integer totalPrice = menu.getMenuPrice() * orderCnt;

       Order saveOrder = Order.builder()
               .store(store)
               .orderStatus(OrderStatus.COMPLETION)
               .totalPrice(totalPrice)
               .build();

       orderRepository.save(saveOrder);

    }
}
