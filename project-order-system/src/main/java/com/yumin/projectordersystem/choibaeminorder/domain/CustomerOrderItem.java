package com.yumin.projectordersystem.choibaeminorder.domain;

import com.yumin.projectordersystem.choibaeminorder.dto.CustomerOrderItemRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@ToString
@Getter
@Entity(name = "customer_order_item") // entity 와 table 명 같게 설정
@NoArgsConstructor
public class CustomerOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "order_cnt")
    private Integer menuCnt;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Builder

    public CustomerOrderItem(Long orderId, Long menuId, Integer menuCnt) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.menuCnt = menuCnt;
    }

    public static CustomerOrderItem createEnrollCustomerOrderItem(Long orderId, CustomerOrderItemRequestDto customerOrderItemRequestDto) {
        return CustomerOrderItem.builder()
                .orderId(orderId)
                .menuId(customerOrderItemRequestDto.getMenuId())
                .menuCnt(customerOrderItemRequestDto.getMenuCnt())
                .build();
    }
}
