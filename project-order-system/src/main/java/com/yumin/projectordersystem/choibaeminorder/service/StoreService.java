package com.yumin.projectordersystem.choibaeminorder.service;

import com.yumin.projectordersystem.choibaeminorder.dto.StoreResponseDto;
import com.yumin.projectordersystem.choibaeminorder.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    public List<StoreResponseDto> getStoreList() {
        return storeRepository
                .findAll()
                .stream()
                .map(StoreResponseDto::of)
                .collect(Collectors.toList());
    }
}
