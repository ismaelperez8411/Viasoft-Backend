package com.viasoft.viasoft.repos;

import java.util.List;

import com.viasoft.viasoft.models.InvoiceHistoryView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface InvoiceViewRepository extends JpaRepository<InvoiceHistoryView, Integer> {
    
    @Query("from InvoiceHistoryView inv where inv.datetime = (select max(datetime) from InvoiceHistoryView) and ( inv.autorizador LIKE %?1% )")
    public List<InvoiceHistoryView> currents(String aut); 

    @Query("select inv from InvoiceHistoryView inv where inv.datetime = (select max(datetime) from InvoiceHistoryView) and inv.autorizador LIKE %?1%")
    public List<InvoiceHistoryView> findAllByAutorizadorContaining(String autorizador);

    public Page<InvoiceHistoryView> findAllByDatetimeContaining(String fecha,Pageable paging);

    public List<InvoiceHistoryView> findAllByDatetimeContainingOrderByHistIdDesc(String time);

    @Query("select distinct datetime from InvoiceHistoryView")
    public List<String> AllDatetime();
}
