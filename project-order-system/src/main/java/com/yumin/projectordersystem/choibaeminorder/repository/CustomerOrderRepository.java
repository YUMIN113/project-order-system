package com.yumin.projectordersystem.choibaeminorder.repository;

import com.yumin.projectordersystem.choibaeminorder.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
