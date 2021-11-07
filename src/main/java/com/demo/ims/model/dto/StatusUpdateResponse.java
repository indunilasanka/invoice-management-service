package com.demo.ims.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"invoiceId", "message"})
public class StatusUpdateResponse {
    private long invoiceId;
    private String message;

    public StatusUpdateResponse(long invoiceId, String message) {
        this.invoiceId = invoiceId;
        this.message = message;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
