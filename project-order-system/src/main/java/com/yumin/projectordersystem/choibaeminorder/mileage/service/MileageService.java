package com.yumin.projectordersystem.choibaeminorder.mileage.service;

import com.yumin.projectordersystem.choibaeminorder.dto.CustomerOrderItemRequestDto;
import com.yumin.projectordersystem.choibaeminorder.mileage.domain.Mileage;
import com.yumin.projectordersystem.choibaeminorder.mileage.repository.MileageRepository;
import com.yumin.projectordersystem.choibaeminorder.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Long.sum;

@Service
public class MileageService {

    private final MileageRepository mileageRepository;

    private final MenuRepository menuRepository;

    public MileageService(MileageRepository mileageRepository, MenuRepository menuRepository) {
        this.mileageRepository = mileageRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional(readOnly = false)
    public void saveMileage (Long memberId, Integer totalPrice) {

        Integer mileageScore = (int) Math.ceil(totalPrice * 5.0 / 100);


        // mileage DB 에 이미 있는 회원의 경우, mileageScore 업데이트 (즉, 이전 구매 경험이 있는 회원)
        if (mileageRepository.findByMemberId(memberId) != null) {

            Integer finalMileageScore = (int) sum(mileageRepository.findByMemberId(memberId).get().getMileageScore(), mileageScore);

            mileageRepository.findByMemberId(memberId).ifPresent(it ->
                    it.updateSaveMileage(finalMileageScore));

        }

        // mileage DB 에 없는 회원의 경우, 신규 등록 (즉, 이전 구매 경험이 없는 회원)
        if (mileageRepository.findByMemberId(memberId) == null) {

            Mileage saveMileage = Mileage.createSaveMileage(memberId, mileageScore);
            mileageRepository.save(saveMileage);

        }
    }

    // 주문 취소 시, 취소한 금액 만큼 적립한 마일리지 취소
    public void subtractMileage(List<CustomerOrderItemRequestDto> customerOrderItemRequestDtoList, Long memberId) {

        Integer cancelTotalPrice = customerOrderItemRequestDtoList.stream().map(it -> {
            return menuRepository.findById(it.getMenuId()).get().getMenuPrice() * it.getMenuCnt();
        }).reduce( (x, y) -> x + y).orElse(0);

        Integer cancelMileage = (int) Math.ceil(cancelTotalPrice * 5.0 / 100);

        Integer finalMileageScore = mileageRepository.findByMemberId(memberId).get().getMileageScore() - (int) cancelMileage;

        mileageRepository.findByMemberId(memberId).ifPresent(it ->
                it.updateSaveMileage(finalMileageScore));

    }
}
