package com.example.coffeehouse.nhom2.model;

public class LichSu {
    int ID;
    int IDNH;
    String NameNH;
    int IDTK;
    String imgNH;
    int TrangThai;
    int SoBan;
    int BuaAn;
    int idBanAn;

    public LichSu(int ID, int IDNH, String nameNH, int IDTK, String imgNH, int trangThai, int soBan, int buaAn, int idBanAn) {
        this.ID = ID;
        this.IDNH = IDNH;
        NameNH = nameNH;
        this.IDTK = IDTK;
        this.imgNH = imgNH;
        TrangThai = trangThai;
        SoBan = soBan;
        BuaAn = buaAn;
        this.idBanAn = idBanAn;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDNH() {
        return IDNH;
    }

    public void setIDNH(int IDNH) {
        this.IDNH = IDNH;
    }

    public String getNameNH() {
        return NameNH;
    }

    public void setNameNH(String nameNH) {
        NameNH = nameNH;
    }

    public int getIDTK() {
        return IDTK;
    }

    public void setIDTK(int IDTK) {
        this.IDTK = IDTK;
    }

    public String getImgNH() {
        return imgNH;
    }

    public void setImgNH(String imgNH) {
        this.imgNH = imgNH;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getSoBan() {
        return SoBan;
    }

    public void setSoBan(int soBan) {
        SoBan = soBan;
    }

    public int getBuaAn() {
        return BuaAn;
    }

    public void setBuaAn(int buaAn) {
        BuaAn = buaAn;
    }

    public int getIdBanAn() {
        return idBanAn;
    }

    public void setIdBanAn(int idBanAn) {
        this.idBanAn = idBanAn;
    }
}
