package org.example;

public class Pesanan {
    private MenuMakanan menuMakanan;
    private int jumlah;

    public Pesanan(MenuMakanan menuMakanan, int jumlah) {
        this.menuMakanan = menuMakanan;
        this.jumlah = jumlah;
    }

    public int hitungTotal() {
        return menuMakanan.getHarga() * jumlah;
    }

    public String toString() {
        return menuMakanan.getNama() + " - " + jumlah + " porsi";
    }
}
