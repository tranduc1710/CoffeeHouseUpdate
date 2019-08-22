package com.example.coffeehouse.nhom2.model;

public class CoffeeHouse {
    int id;
    String tennhahang;
    String diachi;
    String monan;
    String giatien;
    int danhgia;
    String imgnhahang;
    String mota;

    public CoffeeHouse(int id, String tennhahang, String diachi, String monan, String giatien, int danhgia, String imgnhahang, String mota) {
        this.id = id;
        this.tennhahang = tennhahang;
        this.diachi = diachi;
        this.monan = monan;
        this.giatien = giatien;
        this.danhgia = danhgia;
        this.imgnhahang = imgnhahang;
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTennhahang() {
        return tennhahang;
    }

    public void setTennhahang(String tennhahang) {
        this.tennhahang = tennhahang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMonan() {
        return monan;
    }

    public void setMonan(String monan) {
        this.monan = monan;
    }

    public String getGiatien() {
        return giatien;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }

    public int getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(int danhgia) {
        this.danhgia = danhgia;
    }

    public String getImgnhahang() {
        return imgnhahang;
    }

    public void setImgnhahang(String imgnhahang) {
        this.imgnhahang = imgnhahang;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
