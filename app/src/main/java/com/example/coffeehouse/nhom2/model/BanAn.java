package com.example.coffeehouse.nhom2.model;

public class BanAn {
    int iD;
    int iDNhaHang;
    int buaAn;
    int soNguoi;
    int trangThai;
    int soBan;
    String nameNH;
    String imgBuaAn;

    public BanAn(int iD, int iDNhaHang, int buaAn, int soNguoi, int trangThai, int soBan, String nameNH, String imgBuaAn) {
        this.iD = iD;
        this.iDNhaHang = iDNhaHang;
        this.buaAn = buaAn;
        this.soNguoi = soNguoi;
        this.trangThai = trangThai;
        this.soBan = soBan;
        this.nameNH = nameNH;
        this.imgBuaAn = imgBuaAn;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getiDNhaHang() {
        return iDNhaHang;
    }

    public void setiDNhaHang(int iDNhaHang) {
        this.iDNhaHang = iDNhaHang;
    }

    public int getBuaAn() {
        return buaAn;
    }

    public void setBuaAn(int buaAn) {
        this.buaAn = buaAn;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoBan() {
        return soBan;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public String getNameNH() {
        return nameNH;
    }

    public void setNameNH(String nameNH) {
        this.nameNH = nameNH;
    }

    public String getImgBuaAn() {
        return imgBuaAn;
    }

    public void setImgBuaAn(String imgBuaAn) {
        this.imgBuaAn = imgBuaAn;
    }
}
