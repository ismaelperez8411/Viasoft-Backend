package com.viasoft.viasoft.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT"
        + "((( ( ( ( `v`.`autorizacion` + `v`.`autorizacion_devolucion` ) + `v`.`consulta_protocolo` ) + `v`.`consulta_registro` ) + `v`.`discapacidad` ) + `v`.`estado_servicio`  "
        + ") + `v`.`recepcion_eventos`  " + ") AS `disponibilidad_servicio`, " + "`v`.`id` AS `id`, "
        + "`v`.`autorizador` AS `autorizador`, " + "`v`.`autorizacion` AS `autorizacion`, "
        + "`v`.`autorizacion_devolucion` AS `autorizacion_devolucion`, "
        + "`v`.`consulta_protocolo` AS `consulta_protocolo`, " + "`v`.`consulta_registro` AS `consulta_registro`, "
        + "`v`.`discapacidad` AS `discapacidad`, " + "`v`.`estado_servicio` AS `estado_servicio`, "
        + "`v`.`recepcion_eventos` AS `recepcion_eventos`  " + "FROM " + "( " + "SELECT "
        + "`viasoftdb`.`invoice`.`id` AS `id`, " + "`viasoftdb`.`invoice`.`autorizador` AS `autorizador`, "
        + "sum( `viasoftdb`.`invoice_history`.`autorizacion` ) AS `autorizacion`, "
        + "sum( `viasoftdb`.`invoice_history`.`autorizacion_devolucion` ) AS `autorizacion_devolucion`, "
        + "sum( `viasoftdb`.`invoice_history`.`consulta_protocolo` ) AS `consulta_protocolo`, "
        + "sum( `viasoftdb`.`invoice_history`.`consulta_registro` ) AS `consulta_registro`, "
        + "sum( `viasoftdb`.`invoice_history`.`discapacidad` ) AS `discapacidad`, "
        + "sum( `viasoftdb`.`invoice_history`.`estado_servicio` ) AS `estado_servicio`, "
        + "sum( `viasoftdb`.`invoice_history`.`recepcion_eventos` ) AS `recepcion_eventos`  " + "FROM "
        + "( `viasoftdb`.`invoice` JOIN `viasoftdb`.`invoice_history` ON ( ( `viasoftdb`.`invoice_history`.`main_service_id` = `viasoftdb`.`invoice`.`id` ) ) )  "
        + "GROUP BY " + "`viasoftdb`.`invoice`.`autorizador`  " + ") `v`  " + "ORDER BY " + "( " + "( "
        + "( ( ( ( `v`.`autorizacion` + `v`.`autorizacion_devolucion` ) + `v`.`consulta_protocolo` ) + `v`.`consulta_registro` ) + `v`.`discapacidad` ) + `v`.`estado_servicio`  "
        + ") + `v`.`recepcion_eventos` " + ")")
public class InvoiceAvailableView {

    private Integer disponibilidad_servicio;
    @Id
    private Integer id;
    private String autorizador;
    private Integer autorizacion;
    private Integer autorizacionDevolucion;
    private Integer consultaProtocolo;
    private Integer consultaRegistro;
    private Integer discapacidad;
    private Integer estadoServicio;
    private Integer recepcion_eventos;

    public Integer getDisponibilidadServicio() {
        return disponibilidad_servicio;
    }

    public Integer getId() {
        return id;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public Integer getAutorizacion() {
        return autorizacion;
    }

    public Integer getAutorizacionDevolucion() {
        return autorizacionDevolucion;
    }

    public Integer getConsultaProtocolo() {
        return consultaProtocolo;
    }

    public Integer getConsultaRegistro() {
        return consultaRegistro;
    }

    public Integer getEstadoServicio() {
        return discapacidad;
    }

    public Integer getDiscapacidad() {
        return estadoServicio;
    }

    public Integer getRecepcionEventos() {
        return recepcion_eventos;
    }

}
