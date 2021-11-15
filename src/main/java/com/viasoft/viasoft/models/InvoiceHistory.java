package com.viasoft.viasoft.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class InvoiceHistory {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice mainService;

    private Integer autorizacion;

    private Integer autorizacion_devolucion;

    private Integer discapacidad;

    private Integer consulta_protocolo;

    private Integer estado_servicio;

    private String tiempo_medio;

    private Integer consulta_registro;

    private Integer recepcion_eventos;

    private Timestamp timeget;

    @Transient
    private String autorizador;

    public InvoiceHistory() {

    }

    /*
    inv.autorizador,
	invh.autorizacion,
	invh.autorizacion_devolucion,
	invh.consulta_protocolo,
	invh.consulta_registro,
	invh.discapacidad,
	invh.estado_servicio,
	invh.recepcion_eventos,
	invh.tiempo_medio 
    */
    public InvoiceHistory(
            String autorizador, 
            Integer autorizacion, 
            Integer autorizacion_devolucion,
            Integer consulta_protocolo, 
            Integer consulta_registro, 
            Integer discapacidad, 
            Integer estado_servicio,
            Integer recepcion_eventos, 
            String tiempo_medio) {
        this.autorizador = autorizador;
        this.autorizacion = autorizacion;
        this.autorizacion_devolucion = autorizacion_devolucion;
        this.consulta_protocolo = consulta_protocolo;
        this.consulta_registro = consulta_registro;
        this.discapacidad = discapacidad;
        this.estado_servicio = estado_servicio;
        this.recepcion_eventos = recepcion_eventos;
        this.tiempo_medio = tiempo_medio;
    }

    public String getAutorizador(){
        return this.autorizador;
    }

    /*@Override
    public String toString() {
        return "InvoiceHistory{" + "autorizador='" + autorizador + '\'' + ", autorizacion=" + autorizacion + '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InvoiceHistory))
            return false;
        return id != null && id.equals(((InvoiceHistory) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /*public Invoice getMainService() {
        return mainService;
    }*/

    public void setMainService(Invoice ms) {
        this.mainService = ms;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getTimeget() {
        return timeget;
    }

    public void setTimeget(Timestamp timeget) {
        this.timeget = timeget;
    }

    public String getTiempoMedio() {
        return tiempo_medio;
    }

    public void setTiempoMedio(String tiempoMedio) {
        this.tiempo_medio = tiempoMedio;
    }

    public Integer getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Integer autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Integer getAutorizacionDevolucion() {
        return autorizacion_devolucion;
    }

    public void setAutorizacionDevolucion(Integer autorizacionDevolucion) {
        this.autorizacion_devolucion = autorizacionDevolucion;
    }

    public Integer getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(Integer discapacidad) {
        this.discapacidad = discapacidad;
    }

    public Integer getConsultaProtocolo() {
        return consulta_protocolo;
    }

    public void setConsultaProtocolo(Integer consultaProtocolo) {
        this.consulta_protocolo = consultaProtocolo;
    }

    public Integer getEstadoServicio() {
        return estado_servicio;
    }

    public void setEstadoServicio(Integer estadoServicio) {
        this.estado_servicio = estadoServicio;
    }

    public Integer getConsultaRegistro() {
        return consulta_registro;
    }

    public void setConsultaRegistro(Integer consultaRegistro) {
        this.consulta_registro = consultaRegistro;
    }

    public Integer getRecepcionEventos() {
        return recepcion_eventos;
    }

    public void setRecepcionEventos(Integer recepcionEventos) {
        this.recepcion_eventos = recepcionEventos;
    }

}
