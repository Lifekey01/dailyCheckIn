package com.lifekey.dailycheckin.model;

import java.util.Date;

public class Model {

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", namaKegiatan='" + namaKegiatan + '\'' +
                ", id_tanggal='" + id_tanggal + '\'' +
                ", tanggalKegiatan='" + tanggalKegiatan + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    String id;
    String namaKegiatan;
    String id_tanggal;
    String tanggalKegiatan;
    String Status;

    public Model(String id, String namaKegiatan,String id_tanggal, String tanggalKegiatan, String status) {
        this.id = id;
        this.namaKegiatan = namaKegiatan;
        this.id_tanggal = id_tanggal;
        this.tanggalKegiatan = tanggalKegiatan;
        Status = status;
    }

    public Model(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_tanggal() {
        return id_tanggal;
    }

    public void setId_tanggal(String id_tanggal) {
        this.id_tanggal = id_tanggal;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getTanggalKegiatan() {
        return tanggalKegiatan;
    }

    public void setTanggalKegiatan(String tanggalKegiatan) {
        this.tanggalKegiatan = tanggalKegiatan;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
