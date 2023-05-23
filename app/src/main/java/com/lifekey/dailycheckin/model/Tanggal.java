package com.lifekey.dailycheckin.model;

import java.util.Date;

public class Tanggal {
    String id;
    String tanggal;

    public Tanggal(String id, String tanggal) {
        this.id = id;
        this.tanggal = tanggal;
    }

    public Tanggal() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
