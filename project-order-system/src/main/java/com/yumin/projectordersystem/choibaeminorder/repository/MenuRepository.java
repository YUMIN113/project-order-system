package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
