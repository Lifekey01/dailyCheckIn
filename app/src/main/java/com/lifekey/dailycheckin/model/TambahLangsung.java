package com.lifekey.dailycheckin.model;

public class TambahLangsung {
    String id;
    String id_tanggal;
    String id_model;
    String tanggal;
    String kegiatan;

    public TambahLangsung(String id, String id_tanggal, String id_model, String tanggal, String kegiatan) {
        this.id = id;
        this.id_tanggal = id_tanggal;
        this.id_model = id_model;
        this.tanggal = tanggal;
        this.kegiatan = kegiatan;
    }

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

    public String getId_model() {
        return id_model;
    }

    public void setId_model(String id_model) {
        this.id_model = id_model;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    @Override
    public String toString() {
        return "TambahLangsung{" +
                "id='" + id + '\'' +
                ", id_tanggal='" + id_tanggal + '\'' +
                ", id_model='" + id_model + '\'' +
                ", tanggal='" + tanggal + '\'' +
                ", kegiatan='" + kegiatan + '\'' +
                '}';
    }
}
