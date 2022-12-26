package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 점포 별 메뉴 보여주기
    // JPA Query Method : findByStore(… where menu.storeId = ?)
    public List<Menu> findByStoreId(Long storeId);
}
