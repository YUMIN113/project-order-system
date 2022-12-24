package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.domain.Store;
import com.yumin.projectordersystem.choibaeminorder.enums.StoreCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreRepositoryTests {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void storeSaveTest() {

        Store saveStore = Store.builder()
                .storeName("동명반점")
                .storeCategory(StoreCategory.CHINESE)
                .storeTime("09 ~ 23")
                .build();

        storeRepository.save(saveStore);

    }

    @Test
    public void storeUpdateStoreTimeTest() {
        storeRepository.findByStoreId(3L).ifPresent(it -> {
            it.updateStoreTime("10 ~ 23");
            storeRepository.save(it);
        });
    }

    @Test
    public void storeFindAll() {
        List<Store> storeList = storeRepository.findAll();

        storeList.forEach(it -> log.info(it.toString()));

    }
}
