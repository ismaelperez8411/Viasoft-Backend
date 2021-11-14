package com.viasoft.viasoft.repos;

import java.util.List;

import com.viasoft.viasoft.models.InvoiceHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, String> {

    @Query("select new com.viasoft.viasoft.models.InvoiceHistory( inv.autorizador, "+
                   "invh.autorizacion, "+
                   "invh.autorizacion_devolucion, "+
                   "invh.consulta_protocolo, "+
                   "invh.consulta_registro, "+
                   "invh.discapacidad, "+
                   "invh.estado_servicio, "+
                   "invh.recepcion_eventos, "+
                   "invh.tiempo_medio ) "+
                   "from Invoice inv left join InvoiceHistory invh "+
                   "on inv.id = invh.mainService "+
                   "and invh.timeget = (select max(timeget) from InvoiceHistory)")
    public List<InvoiceHistory> currents();

    
}
