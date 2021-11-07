package com.demo.ims.service.validator;

import com.demo.ims.common.Status;
import com.demo.ims.exception.ErrorEnum;
import com.demo.ims.exception.ValidationFailureException;
import com.demo.ims.model.dto.StatusUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class RequestValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidator.class);

    private RequestValidator() {
    }

    public static Status getValidInvoiceStatus(String statusText) {
        try {
            return Status.valueOf(statusText.toUpperCase());
        } catch (Exception e) {
            LOGGER.error("Invalid invoice status: {}", statusText, e);
            throw new ValidationFailureException(ErrorEnum.INVALID_STATUS, HttpStatus.BAD_REQUEST);
        }
    }

    public static Status validateInvoiceUpdateRequest(StatusUpdateRequest request) {
        if (request.getStatus() == null) {
            LOGGER.error("Null status");
            throw new ValidationFailureException(ErrorEnum.INVALID_STATUS, HttpStatus.BAD_REQUEST);
        } else {
            return RequestValidator.getValidInvoiceStatus(request.getStatus());
        }
    }

}
