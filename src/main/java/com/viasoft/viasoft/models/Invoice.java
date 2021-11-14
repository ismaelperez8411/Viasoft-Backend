package com.viasoft.viasoft.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLInsert;

@Entity
@Table(name = "invoice", uniqueConstraints = @UniqueConstraint(columnNames = { "autorizador" }))
@SQLInsert(sql = "INSERT IGNORE INTO invoice(autorizador) " + "VALUES (?)")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "autorizador", nullable = false)
    private String autorizador;

    @OneToMany(mappedBy = "mainService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceHistory> serviceHistories = new ArrayList<>();

    public void addServiceHistory(InvoiceHistory sh) {
        serviceHistories.add(sh);
        sh.setMainService(this);
    }

    public void removeServiceHistory(InvoiceHistory sh) {
        serviceHistories.remove(sh);
        sh.setMainService(null);
    }

    public Invoice() {
        this.serviceHistories = new ArrayList<>();
    }

    public Invoice(String autorizador) {
        this.autorizador = autorizador;
        this.serviceHistories = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InvoiceHistory> getServiceHistories() {
        return this.serviceHistories;
    }

    public void setServiceHistories(List<InvoiceHistory> sh) {
        this.serviceHistories = sh;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

}
