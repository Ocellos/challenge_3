package org.example;

public class MenuMakanan {
    private String nama;
    private int harga;

    public MenuMakanan(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }
}
