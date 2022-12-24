package com.yumin.projectordersystem.choibaeminorder.enums;

public enum StoreCategory {

    CHINESE("중식"), JAPANESE("일식"), KOREAN("한식");
    private String categoryDescription;

    StoreCategory(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
