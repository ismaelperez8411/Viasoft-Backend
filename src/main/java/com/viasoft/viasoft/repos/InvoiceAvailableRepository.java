package com.viasoft.viasoft.repos;

import com.viasoft.viasoft.models.InvoiceAvailableView;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceAvailableRepository extends JpaRepository<InvoiceAvailableView, Integer> {
    
}
