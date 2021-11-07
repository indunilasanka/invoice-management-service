package com.demo.ims.service;

import com.demo.ims.model.entity.Invoice;
import com.demo.ims.model.dto.PageResponse;
import com.demo.ims.repository.InvoiceRepository;
import com.demo.ims.service.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Test
    public void getInvoicesByStatus_shouldReturnMatchingInvoices_whenStatusGiven() {
        Page<Invoice> page = Mockito.mock(Page.class);
        when(page.getTotalElements()).thenReturn(3L);

        List<Invoice> invoices = Arrays.asList(new Invoice(), new Invoice(), new Invoice());
        when(page.getContent()).thenReturn(invoices);

        when(invoiceRepository.findAllByStatus(any(), any())).thenReturn(page);

        //verify(seedApiHandler, atLeast(1)).loadPriceZoneDetailsForCustomerGroup(any());
        PageResponse<Invoice> response =
                invoiceService.getInvoicesByStatus("Pending", 0, 10);
        verify(invoiceRepository, times(1)).findAllByStatus(any(), any());


    }

//    @Test
//    public void getInvoicesByStatus_shouldReturnAllInvoices_whenStatusNotGiven(){
//
//    }

}
