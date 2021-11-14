package com.viasoft.viasoft.services;

import java.util.List;

import com.viasoft.viasoft.models.InvoiceAvailableView;
import com.viasoft.viasoft.repos.InvoiceAvailableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceAvailableService {

    @Autowired
    InvoiceAvailableRepository invoiceAvailableRepo;

    //devuelve el historico de los estados de los servicios
    public List<InvoiceAvailableView> getAllRecords() {
        return invoiceAvailableRepo.findAll();
    } 

}
