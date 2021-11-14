package com.viasoft.viasoft.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT " + "`invoice`.`id` AS `inv_id`," + "`invoice_history`.`id` AS `hist_id`,"
        + "`invoice`.`autorizador` AS `autorizador`,"
        + "( SELECT `states`.`desc` FROM `states` WHERE ( `states`.`id` = `invoice_history`.`autorizacion` ) ) AS `autorizacion`,"
        + "( SELECT `states`.`desc` FROM `states` WHERE ( `states`.`id` = `invoice_history`.`autorizacion_devolucion` ) ) AS `autorizacion_devolucion`,"
        + "( SELECT `states`.`desc` FROM `states` WHERE ( `states`.`id` = `invoice_history`.`consulta_protocolo` ) ) AS `consulta_protocolo`,"
        + "( SELECT `states`.`desc` FROM `states` WHERE ( `states`.`id` = `invoice_history`.`consulta_registro` ) ) AS `consulta_registro`,"
        + "( SELECT `states`.`desc` FROM `states` WHERE ( `states`.`id` = `invoice_history`.`discapacidad` ) ) AS `discapacidad`,"
        + "( SELECT `states`.`desc` FROM `states` WHERE ( `states`.`id` = `invoice_history`.`estado_servicio` ) ) AS `estado_servicio`,"
        + "( SELECT `states`.`desc` FROM `states` WHERE ( `states`.`id` = `invoice_history`.`recepcion_eventos` ) ) AS `recepcion_eventos`,"
        + "`invoice_history`.`tiempo_medio` AS `tiempo_medio`,"
        + "date_format( `invoice_history`.`timeget`, '%m-%d-%Y %H:%i' ) AS `datetime` " + "FROM"
        + "( `invoice` JOIN `invoice_history` ON ( ( `invoice_history`.`main_service_id` = `invoice`.`id` ) ) )")
public class InvoiceHistoryView implements Serializable {

    private Integer invId;
    @Id
    private Integer histId;
    private String autorizador;
    private String autorizacion;
    private String autorizacionDevolucion;
    private String consultaProtocolo;
    private String consultaRegistro;
    private String discapacidad;
    private String estadoServicio;
    private String recepcionEventos;
    private String tiempoMedio;
    private String datetime;

    public Integer getInvId() {
        return invId;
    }

    public Integer getHistId() {
        return histId;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public String getAutorizacionDevolucion() {
        return autorizacionDevolucion;
    }

    public String getConsultaProtocolo() {
        return consultaProtocolo;
    }

    public String getConsultaRegistro() {
        return consultaRegistro;
    }

    public String getEstadoServicio() {
        return discapacidad;
    }

    public String getDiscapacidad() {
        return estadoServicio;
    }

    public String getRecepcionEventos() {
        return recepcionEventos;
    }

    public String getTiempoMedio() {
        return tiempoMedio;
    }

    public String getDatetime() {
        return datetime;
    }

}
