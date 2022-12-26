package com.yumin.projectordersystem.choibaeminorder.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private Integer menuPrice;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Builder
    public Menu(Long storeId, String menuName, Integer menuPrice) {
        this.storeId = storeId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }
}
