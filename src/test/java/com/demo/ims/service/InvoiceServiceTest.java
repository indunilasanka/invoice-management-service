package com.demo.ims.service;

import com.demo.ims.common.Status;
import com.demo.ims.model.dto.PageResponse;
import com.demo.ims.model.entity.Invoice;
import com.demo.ims.repository.InvoiceRepository;
import com.demo.ims.service.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {
    private InvoiceRepository invoiceRepository;
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    public void setup() {
        invoiceRepository = mock(InvoiceRepository.class);
        invoiceService = new InvoiceServiceImpl(invoiceRepository);
    }

    @Test
    public void getInvoicesByStatus_shouldReturnMatchingInvoices_whenStatusGiven() {
        Page<Invoice> page = mock(Page.class);
        when(page.getTotalElements()).thenReturn(3L);

        List<Invoice> invoices = Arrays.asList(new Invoice(), new Invoice(), new Invoice());
        when(page.getContent()).thenReturn(invoices);

        when(invoiceRepository.findAllByStatus(any(Status.class), any(Pageable.class))).thenReturn(page);

        PageResponse<Invoice> response =
                invoiceService.getInvoicesByStatus("Pending", 0, 10);
        verify(invoiceRepository, times(1)).findAllByStatus(any(), any());
        Assertions.assertEquals(3, response.getData().size());
    }

    @Test
    public void getInvoicesByStatus_shouldThrowException_whenStatusInvalid() {
        try {
            invoiceService.getInvoicesByStatus("invalid-status", 0, 10);
        } catch (Exception e) {
            Assertions.assertEquals("Invalid status", e.getMessage());
        }
    }

    @Test
    public void getInvoicesByStatus_shouldReturnAllInvoices_whenStatusNotGiven() {
        Page<Invoice> page = mock(Page.class);
        when(page.getTotalElements()).thenReturn(3L);

        List<Invoice> invoices = Arrays.asList(new Invoice(), new Invoice(), new Invoice());
        when(page.getContent()).thenReturn(invoices);

        when(invoiceRepository.findAll(any(Pageable.class))).thenReturn(page);

        PageResponse<Invoice> response =
                invoiceService.getInvoicesByStatus(null, 0, 10);
        verify(invoiceRepository, times(1)).findAll(any(Pageable.class));
        Assertions.assertEquals(3, response.getData().size());
    }

}
