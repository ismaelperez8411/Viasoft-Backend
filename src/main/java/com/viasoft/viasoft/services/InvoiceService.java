package com.viasoft.viasoft.services;

import javax.transaction.Transactional;

import com.viasoft.viasoft.models.Invoice;
import com.viasoft.viasoft.repos.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    
    @Autowired
    InvoiceRepository repo;

    public Invoice saveService(Invoice nserv) {
        return repo.save(nserv);
    }

    @Transactional
    public Invoice findByAutorizador(String aut) {
        return repo.findByAutorizador(aut);
    }
}
