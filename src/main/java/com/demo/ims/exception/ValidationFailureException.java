package com.demo.ims.exception;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ValidationFailureException extends RuntimeException {
    private final int errorCode;
    private final HttpStatus httpStatusCode;

    public ValidationFailureException(ErrorEnum errorEnum, HttpStatus httpStatusCode) {
        super(errorEnum.getMessage(), null);
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorEnum.getCode();
    }

    public HttpStatus getHttpStatusCode() {
        return Objects.requireNonNullElse(httpStatusCode, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public int getErrorCode() {
        return errorCode;
    }

}
