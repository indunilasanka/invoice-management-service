package com.demo.ims.exception;

public enum ErrorEnum {
    INVALID_STATUS(1000, "Invalid status"), //400
    STATUS_ALREADY_CHANGED(1001, "Invoice status already changed"), //400

    INVOICE_NOT_FOUND(102061, "Provided invoice id is not found"), //404
    UNKNOWN_ERROR(2000, "Unknown error"); //500

    private final int code;
    private final String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
