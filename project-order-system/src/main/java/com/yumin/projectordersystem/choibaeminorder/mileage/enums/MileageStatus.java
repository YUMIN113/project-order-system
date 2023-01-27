package com.yumin.projectordersystem.choibaeminorder.mileage.enums;

public enum MileageStatus {

    SAVE("적립"), USE("사용"), CANCEL("취소");

    private String mileageStatusDescription;

    MileageStatus(String mileageStatusDescription) {
        this.mileageStatusDescription = mileageStatusDescription;
    }

    public String getMileageStatusDescription() {
        return mileageStatusDescription;
    }
}
