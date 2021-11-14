package com.viasoft.viasoft.services;

import java.util.List;

import com.viasoft.viasoft.models.InvoiceHistory;
import com.viasoft.viasoft.repos.InvoiceHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceHistoryService {
    @Autowired
    InvoiceHistoryRepository repo;

    public InvoiceHistory saveServiceHistory(InvoiceHistory nservh) {
        return repo.save(nservh);
    }

    public List<InvoiceHistory> getCurrentStates() {
        return repo.currents();
    }
}
