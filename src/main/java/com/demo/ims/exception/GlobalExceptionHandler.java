package com.demo.ims.exception;

import com.demo.ims.common.Constant;
import com.demo.ims.model.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.demo.ims.exception.ErrorEnum.UNKNOWN_ERROR;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSource messages;

    public GlobalExceptionHandler(@Qualifier("messageSource") MessageSource messages) {
        this.messages = messages;
    }

    @ExceptionHandler(ValidationFailureException.class)
    ResponseEntity<ErrorResponse> handleValidationFailures(ValidationFailureException e) {
        String traceId = getTraceId();

        LOGGER.error("[{}] Validation failure occurred", traceId, e);
        return getResponseEntity(e.getErrorCode(), e.getMessage(), e.getHttpStatusCode());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleUnknownException(Exception e) {
        String traceId = getTraceId();
        LOGGER.error("[{}] Unknown exception occurred", traceId, e);
        return getResponseEntity(UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<ErrorResponse> getResponseEntity(int errorCode, String errorMessage, HttpStatus status) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ErrorResponse error = new ErrorResponse(errorCode, errorMessage);
        return new ResponseEntity<>(error, headers, status);
    }

    private String getTraceId() {
        return MDC.get(Constant.CORRELATION_ID_LOG);
    }


}
