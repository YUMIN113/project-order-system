package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.domain.Mileage;
import com.yumin.projectordersystem.choibaeminorder.repository.MileageRepository;
import org.springframework.stereotype.Service;

@Service
public class MileageService {

    private final MileageRepository mileageRepository;

    public MileageService(MileageRepository mileageRepository) {
        this.mileageRepository = mileageRepository;
    }

    public void saveMileage (Long memberId, Integer totalPrice) {

        Integer mileageScore = (int) Math.ceil(totalPrice * 5.0 / 100);

        // mileage 클래스의 클래스 메서드 사용
        Mileage saveMileage = Mileage.createSaveMileage(memberId, mileageScore);

        mileageRepository.save(saveMileage);
    }
}
