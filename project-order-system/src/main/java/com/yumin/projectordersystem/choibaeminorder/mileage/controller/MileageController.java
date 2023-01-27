package com.yumin.projectordersystem.choibaeminorder.mileage.controller;

import com.yumin.projectordersystem.choibaeminorder.mileage.dto.MemberMileageResponseDto;
import com.yumin.projectordersystem.choibaeminorder.mileage.dto.MileageResponseDto;
import com.yumin.projectordersystem.choibaeminorder.mileage.service.MileageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mileages")
public class MileageController {

    private final MileageService mileageService;

    public MileageController(MileageService mileageService) {
        this.mileageService = mileageService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberMileageResponseDto> findMileageList(@PathVariable(value = "memberId") Long memberId) {
        return ResponseEntity.ok(mileageService.getMemberMileage(memberId));
    }
}
