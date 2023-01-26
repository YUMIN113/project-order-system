package com.yumin.projectordersystem.choibaeminorder.mileage.service;

import com.yumin.projectordersystem.choibaeminorder.mileage.domain.Mileage;
import com.yumin.projectordersystem.choibaeminorder.mileage.enums.MileageStatus;
import com.yumin.projectordersystem.choibaeminorder.mileage.repository.MileageRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MileageService {

    private final MileageRepository mileageRepository;

    private final MenuRepository menuRepository;

    public MileageService(MileageRepository mileageRepository, MenuRepository menuRepository) {
        this.mileageRepository = mileageRepository;
        this.menuRepository = menuRepository;
    }

    // 마일리지 적립
    @Transactional(readOnly = false)
    public void saveMileage (Long orderId, Long memberId, Integer totalPrice) {

        Integer mileageScore = (int) Math.ceil(totalPrice * 5.0 / 100);

        Mileage saveMileage = Mileage.createSaveMileage(orderId, memberId, mileageScore);
        mileageRepository.save(saveMileage);

    }

    // 주문 취소 시, 취소한 주문에 해당하는 마일리지 취소 (dirty checking)
    @Transactional(readOnly = false)
    public void cancelMileage(Long orderId) {

        mileageRepository.findByOrderId(orderId).ifPresent(it ->
                it.updateSaveMileage(MileageStatus.CANCEL));

    }
}
