package com.demo.ims.service.impl;

import com.demo.ims.common.Constant;
import com.demo.ims.common.Status;
import com.demo.ims.exception.ErrorEnum;
import com.demo.ims.exception.ValidationFailureException;
import com.demo.ims.model.dto.StatusUpdateRequest;
import com.demo.ims.model.dto.StatusUpdateResponse;
import com.demo.ims.model.entity.Invoice;
import com.demo.ims.repository.InvoiceRepository;
import com.demo.ims.service.InvoiceService;
import com.demo.ims.service.validator.RequestValidator;
import com.demo.ims.model.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public PageResponse<Invoice> getInvoicesByStatus(String statusText, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Invoice> invoicesPage;

        if (statusText != null) {
            Status status = RequestValidator.getValidInvoiceStatus(statusText);
            invoicesPage = invoiceRepository.findAllByStatus(status, pageable);
        } else {
            invoicesPage = invoiceRepository.findAll(pageable);
        }

        PageResponse<Invoice> invoicesPageResponse = new PageResponse<>();
        invoicesPageResponse.setData(invoicesPage.getContent());
        invoicesPageResponse.setOffset(invoicesPage.getNumber());
        invoicesPageResponse.setLimit((limit));
        invoicesPageResponse.setTotalRecords(invoicesPage.getTotalElements());

        return invoicesPageResponse;
    }

    @Override
    public StatusUpdateResponse updateInvoiceStatus(long invoiceId, StatusUpdateRequest request) {
        Status status = RequestValidator.validateInvoiceUpdateRequest(request);
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);

        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setStatus(status);
            invoiceRepository.save(invoice);
            return new StatusUpdateResponse(invoiceId, Constant.Messages.INVOICE_STATUS_UPDATED);
        } else {
            throw new ValidationFailureException(ErrorEnum.INVOICE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

}
