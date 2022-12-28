package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTests {

    @Autowired
    private OrderService orderService;


    @Test
    public void orderSaveTest() {

        Long menuId = 3L;
        Integer orderCnt = 2;

        orderService.Order(menuId, orderCnt);



    }
}
