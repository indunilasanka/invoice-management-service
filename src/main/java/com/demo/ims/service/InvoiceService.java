package com.demo.ims.service;

import com.demo.ims.model.entity.Invoice;
import com.demo.ims.model.dto.PageResponse;
import com.demo.ims.model.dto.StatusUpdateRequest;
import com.demo.ims.model.dto.StatusUpdateResponse;

public interface InvoiceService {

    PageResponse<Invoice> getInvoicesByStatus(String statusText, int offset, int limit);

    StatusUpdateResponse updateInvoiceStatus(long invoiceId, StatusUpdateRequest request);
}
