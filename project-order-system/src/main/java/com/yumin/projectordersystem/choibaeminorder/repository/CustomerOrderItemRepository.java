package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.domain.CustomerOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderItemRepository extends JpaRepository<CustomerOrderItem, Long> {
}
