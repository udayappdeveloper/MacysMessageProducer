package com.macys.dto;

public enum OrderStatus {
    CREATED("Created"),
    CONFIRMED("Confirmed"),
    SHIPPED("Shipped");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
