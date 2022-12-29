package com.yumin.projectordersystem.choibaeminorder.domain;

import com.yumin.projectordersystem.choibaeminorder.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Getter
@Entity(name = "customer_order")
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "store_id")
    private Long storeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "total_price")
    private Integer totalPrice;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "modify_at")
    private LocalDateTime modifyAt;

    @Builder
    public CustomerOrder(Long storeId,
                         OrderStatus orderStatus,
                         Integer totalPrice) {
        this.storeId = storeId;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }

    public static CustomerOrder createCustomerOrder(Long storeId, Integer totalPrice) {

        return CustomerOrder.builder()
                .storeId(storeId)
                .orderStatus(OrderStatus.PROGRESS)
                .totalPrice(totalPrice)
                .build();

    }
}
