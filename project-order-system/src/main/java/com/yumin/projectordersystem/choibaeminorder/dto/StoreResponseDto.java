package com.yumin.projectordersystem.choibaeminorder.dto;

import com.yumin.projectordersystem.choibaeminorder.domain.Store;
import com.yumin.projectordersystem.choibaeminorder.enums.StoreCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class StoreResponseDto {


    private Long storeId;

    private String storeName;

    private StoreCategory storeCategory;

    private String storeTime;

    private Integer storeScore = 0;

    private LocalDateTime createAt;

    private LocalDateTime modifyAt;

    @Builder
    public StoreResponseDto(Long storeId,
                            String storeName,
                            StoreCategory storeCategory,
                            String storeTime,
                            Integer storeScore,
                            LocalDateTime createAt,
                            LocalDateTime modifyAt) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storeTime = storeTime;
        this.storeScore = storeScore;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
    }

    public static StoreResponseDto of(Store store) {
        return StoreResponseDto.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .storeCategory(store.getStoreCategory())
                .storeTime(store.getStoreTime())
                .storeScore(store.getStoreScore())
                .createAt(store.getCreateAt())
                .modifyAt(store.getModifyAt())
                .build();
    }
}
