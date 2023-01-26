package com.yumin.projectordersystem.choibaeminorder.mileage.repository;

import com.yumin.projectordersystem.choibaeminorder.mileage.domain.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MileageRepository extends JpaRepository<Mileage, Long> {

    public List<Mileage> findByMemberId(Long memberId);

    public Optional<Mileage> findByOrderId(Long orderId);


}
