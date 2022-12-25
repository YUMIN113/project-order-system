package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.domain.Menu;
import com.yumin.projectordersystem.choibaeminorder.domain.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuRepositoryTests {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private StoreRepository storeRepository;


    @Test
    public void menuSaveTest() {

        // store 에서 store_id 찾아서 menu 의 store_id column 에 등록하기 위한 과정
        Optional<Store> storeWrapper = storeRepository.findByStoreId(3L);
        Store store = storeWrapper.get();

        Menu saveMenu = Menu.builder()
                .menuName("츠케멘")
                .menuPrice(13000)
                .store(store)
                .build();

        menuRepository.save(saveMenu);
    }
}
