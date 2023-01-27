package com.yumin.projectordersystem.choibaeminorder.mileage.service;

import com.yumin.projectordersystem.choibaeminorder.mileage.domain.Mileage;
import com.yumin.projectordersystem.choibaeminorder.mileage.dto.MemberMileageResponseDto;
import com.yumin.projectordersystem.choibaeminorder.mileage.dto.MileageResponseDto;
import com.yumin.projectordersystem.choibaeminorder.mileage.enums.MileageStatus;
import com.yumin.projectordersystem.choibaeminorder.mileage.repository.MileageRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

    // 개인별 마일리지 List
    @Transactional(readOnly = true)
    public MemberMileageResponseDto getMemberMileage(Long memberId) {

        List<MileageResponseDto> mileageResponseDtoList = getMileageResponseDtoList(memberId);

        // mileageStatus 가 SAVE("적립") 인 경우의 mileageScore 총 합
        Integer totalMileage = getTotalMileage(mileageResponseDtoList);

        return MemberMileageResponseDto.of(totalMileage, mileageResponseDtoList);
    }
    
    private List<MileageResponseDto> getMileageResponseDtoList(Long memberId) {
        return mileageRepository
                .findByMemberId(memberId)
                .stream()
                .map(MileageResponseDto::of)
                .collect(Collectors.toList());
    }

    private static Integer getTotalMileage(List<MileageResponseDto> mileageResponseDtoList) {
        return mileageResponseDtoList.stream()
                .filter(it -> MileageStatus.SAVE.equals(it.getMileageStatus()))
                .map(it -> it.getMileageScore())
                .reduce((x, y) -> x + y).orElse(0);
    }
}
