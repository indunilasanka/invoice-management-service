package com.demo.ims.controller;

import com.demo.ims.model.dto.PageResponse;
import com.demo.ims.model.entity.Invoice;
import com.demo.ims.service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private InvoiceService invoiceService;
    private InvoiceController invoiceController;

    @BeforeEach
    public void setup() {
        invoiceService = mock(InvoiceService.class);
        invoiceController = new InvoiceController(invoiceService);
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    public void getPricesForPriceManagerTest_ShouldReturnIsOk_whenRequestIsValid() throws Exception {
        PageResponse<Invoice> response = new PageResponse();
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setCustomer("ABC-Customer");
        response.setData(Arrays.asList(invoice, new Invoice(), new Invoice()));
        response.setTotalRecords(3);
        response.setOffset(0);
        response.setLimit(10);

        given(invoiceService.getInvoicesByStatus(any(String.class), any(Integer.class), any(Integer.class))).willReturn(response);

        mockMvc.perform(get("/ims/v1/invoices/PENDING"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalRecords", is(3)))
                .andExpect(jsonPath("$.limit", is(10)))
                .andExpect(jsonPath("$.data").isArray());
    }
}
