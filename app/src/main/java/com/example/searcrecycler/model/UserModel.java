package com.example.searcrecycler.model;

public class UserModel {
    int pic;
    String nama;
    String email;

    public UserModel(int pic, String nama, String email) {
        this.pic = pic;
        this.nama = nama;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
