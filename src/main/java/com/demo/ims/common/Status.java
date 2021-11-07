package com.demo.ims.common;

public enum Status {
    PENDING("PENDING"),
    APPROVED("APPROVED");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
