package com.viasoft.viasoft.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viasoft.viasoft.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    Invoice findByAutorizador(String autorizador);
}
