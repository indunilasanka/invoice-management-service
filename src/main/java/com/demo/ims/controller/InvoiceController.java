package com.demo.ims.controller;

import com.demo.ims.model.dto.StatusUpdateRequest;
import com.demo.ims.model.dto.StatusUpdateResponse;
import com.demo.ims.model.entity.Invoice;
import com.demo.ims.service.InvoiceService;
import com.demo.ims.model.dto.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/ims/")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping({"invoices", "invoices/{status}"})
    public ResponseEntity<PageResponse<Invoice>> getInvoices(
            @PathVariable(value = "status", required = false) String status,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        PageResponse<Invoice> response = invoiceService.getInvoicesByStatus(status, offset, limit);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("invoices/{invoice_id}")
    public ResponseEntity<StatusUpdateResponse> updateInvoiceStatus(
            @PathVariable(value = "invoice_id", required = false) long invoiceId,
            @RequestBody StatusUpdateRequest request) {
        StatusUpdateResponse response = invoiceService.updateInvoiceStatus(invoiceId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
