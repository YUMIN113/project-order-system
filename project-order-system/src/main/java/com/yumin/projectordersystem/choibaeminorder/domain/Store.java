package com.yumin.projectordersystem.choibaeminorder.domain;

import com.yumin.projectordersystem.choibaeminorder.enums.StoreCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@ToString
@Entity
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_category")
    @Enumerated(EnumType.STRING)
    private StoreCategory storeCategory;

    @Column(name = "store_time")
    private String storeTime;

    @Column(name = "store_score")
    private Integer storeScore = 0;

    @CreationTimestamp // 처음 생성시
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp // 수정시
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Builder
    public Store(String storeName, StoreCategory storeCategory, String storeTime) {
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storeTime = storeTime;
        this.storeScore = 0;

    }

    public void updateStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }
}
