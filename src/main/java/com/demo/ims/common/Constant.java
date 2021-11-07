package com.demo.ims.common;

public class Constant {

    public static final String DEFAULT_OFFSET = "0";
    public static final String DEFAULT_LIMIT = "100";
    public static final String CORRELATION_ID_LOG = "correlationId";

    public static class Messages {
        public static String INVOICE_STATUS_UPDATED = "Invoice status updated successfully";
        public static String INVALID_INVOICE_STATUS = "Invalid invoice status";
    }

    public static class RequestHeader {
        public static final String CORRELATION_ID = "X-Syy-Correlation-Id";
        public static final String ACCESS_CONTROL_ORIGIN = "Access-Control-Allow-Origin";
        public static final String CLIENT_IP = "X-Forwarded-For";
    }
}
