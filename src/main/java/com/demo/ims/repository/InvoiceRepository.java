package com.demo.ims.repository;

import com.demo.ims.common.Status;
import com.demo.ims.model.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Page<Invoice> findAllByStatus(Status status, Pageable pageable);
}
