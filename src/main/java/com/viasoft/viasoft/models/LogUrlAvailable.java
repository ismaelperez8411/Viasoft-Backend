package com.viasoft.viasoft.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class LogUrlAvailable {

    @Id
    private Timestamp datetime;
    @Size(min = 0, max = 1500)
    private String msg;
    private Boolean active;

    public LogUrlAvailable(){}
    public LogUrlAvailable(Timestamp datetime, String msg, Boolean active) {
        this.datetime = datetime;
        this.msg = msg;
        this.active = active;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getActive() {
        return active;
    }
}
