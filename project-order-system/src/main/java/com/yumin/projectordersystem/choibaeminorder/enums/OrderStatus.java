package com.yumin.projectordersystem.choibaeminorder.enums;

public enum OrderStatus {

    ONGOING("주문중"), COMPLETION("주문완료");

    private String orderStatusDescription;

    OrderStatus(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
    }
}
