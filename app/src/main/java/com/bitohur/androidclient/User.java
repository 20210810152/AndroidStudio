package com.bitohur.androidclient;

public class User {
    private int id;
    private String nama;
    private String nim;
    private String jk;
    private String fakultas;
    private String kelas;
    private String agama;
    private String alamat;

    public User(String nama, String nim, String jk, String fakultas, String kelas, String agama, String alamat) {
        this.nama = nama;
        this.nim = nim;
        this.jk = jk;
        this.fakultas = fakultas;
        this.kelas = kelas;
        this.agama = agama;
        this.alamat = alamat;
    }

    public User(int id, String nama, String nim, String jk, String fakultas, String kelas, String agama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.jk = jk;
        this.fakultas = fakultas;
        this.kelas = kelas;
        this.agama = agama;
        this.alamat = alamat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public void setFakultas(String fakultas) {this.fakultas = fakultas;}
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    public void setAgama(String agama) {
        this.agama = agama;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }
    public String getNim() {
        return nim;
    }

    public String getJk() {return jk;}
    public String getFakultas() {
        return fakultas;
    }
    public String getKelas() {
        return kelas;
    }
    public String getAgama() {
        return agama;
    }
    public String getAlamat() {
        return alamat;
    }

}
