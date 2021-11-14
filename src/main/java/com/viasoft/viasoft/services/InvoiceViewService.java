package com.viasoft.viasoft.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.viasoft.viasoft.models.InvoiceHistoryView;
import com.viasoft.viasoft.repos.InvoiceViewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceViewService {

    @Autowired
    InvoiceViewRepository invoiceViewRepo;

    // devuelve el estado actual de los servicios
    public List<InvoiceHistoryView> getCurrentStates(String aut) {
        return invoiceViewRepo.currents(aut);
    }

    // devuelve el historico de los estados de los servicios
    // public List<InvoiceHistoryView> getAllRecords(Integer pageNo, Integer
    // pageSize, String sortBy) {
    public Map<String, Object> getAllRecords(Integer pageNo, Integer pageSize, String sortBy, String fecha,
            String sortOrder) {

        Sort sor  = sortOrder.equals("1") ? Sort.by(Direction.ASC, sortBy):Sort.by(Direction.DESC, sortBy);
        Pageable paging = PageRequest.of(pageNo, pageSize, sor);
        Page<InvoiceHistoryView> pagedResult = (fecha == "") ? invoiceViewRepo.findAll(paging)
                : invoiceViewRepo.findAllByDatetimeContaining(fecha, paging);

        Map<String, Object> response = new HashMap<>();
        if (pagedResult.hasContent()) {
            // return pagedResult.getContent();
            response.put("invoices", pagedResult.getContent());
            response.put("currentPage", pagedResult.getNumber());
            response.put("totalItems", pagedResult.getTotalElements());
            response.put("totalPages", pagedResult.getTotalPages());
            return response;
        } else {
            return response;
        }
    }

    // devuelve el historico de los estados de los servicios filtrando por
    // autorizador
    public List<InvoiceHistoryView> getStatesForAutorizador(String autorizador) {
        return invoiceViewRepo.findAllByAutorizadorContaining(autorizador);
    }

    // devuelve el historico de los estados de los servicios filtrando por fecha
    public List<InvoiceHistoryView> getStatesForTime(String timeget) {
        return invoiceViewRepo.findAllByDatetimeContainingOrderByHistIdDesc(timeget);
    }

    // devuelve todas las fecha y horas que estan registrdas en la bd
    public List<String> getAllDatetime() {
        return invoiceViewRepo.AllDatetime();
    }

}
