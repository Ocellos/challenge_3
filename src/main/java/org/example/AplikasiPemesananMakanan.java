package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AplikasiPemesananMakanan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<MenuMakanan> daftarMenu = List.of(
                new MenuMakanan("Nasi Goreng", 15000),
                new MenuMakanan("Mie Ayam", 12000),
                new MenuMakanan("Soto Ayam", 10000)
        );

        List<Pesanan> pesanan = List.of();

        int pilihan;
        do {
            System.out.println("=== Menu Utama ===");
            System.out.println("1. Lihat Menu Makanan");
            System.out.println("2. Pesan Makanan");
            System.out.println("3. Keluar dari Aplikasi");
            System.out.print("Silakan pilih menu (1/2/3): ");

            pilihan = getValidInput(input);

            switch (pilihan) {
                case 1 -> {
                    System.out.println("=== Menu Makanan ===");
                    IntStream.range(0, daftarMenu.size())
                            .forEach(i -> System.out.println((i + 1) + ". " + daftarMenu.get(i).getNama() + " - " + daftarMenu.get(i).getHarga() + " IDR"));
                }
                case 2 -> {
                    System.out.print("Masukkan nomor menu yang ingin dipesan (1/2/3): ");
                    int nomorMenu = getValidInput(input);

                    if (isValidMenuNumber(nomorMenu, daftarMenu.size())) {
                        System.out.print("Masukkan jumlah pesanan: ");
                        int jumlahPesanan = getValidInput(input);

                        MenuMakanan menuDipesan = daftarMenu.get(nomorMenu - 1);
                        Pesanan pesan = new Pesanan(menuDipesan, jumlahPesanan);
                        pesanan = addToPesanan(pesanan, pesan);
                        System.out.println(pesan + " telah ditambahkan ke pesanan Anda.");
                    } else {
                        System.out.println("Nomor menu tidak valid.");
                    }
                }
                case 3 -> {
                    System.out.println("=== Konfirmasi dan Pembayaran ===");
                    int totalPembayaran = calculateTotalPembayaran(pesanan);
                    if (!pesanan.isEmpty()) {
                        System.out.println("Pesanan Anda:");
                        pesanan.forEach(p -> System.out.println("- " + p.toString()));
                        System.out.println("Total Pembayaran: " + totalPembayaran + " IDR");
                        System.out.print("Apakah Anda ingin mengkonfirmasi pesanan dan membayar (ya/tidak)? ");
                        String konfirmasi = input.next().toLowerCase();
                        if (konfirmasi.equals("ya")) {
                            System.out.println("Terima kasih atas pesanan Anda!");
                            pesanan = List.of(); // Clearing the pesanan by creating a new immutable empty list
                        } else {
                            System.out.println("Pesanan dibatalkan.");
                        }
                    } else {
                        System.out.println("Tidak ada pesanan yang dibuat.");
                    }
                }
                default -> System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }

        } while (pilihan != 3);

        input.close();
    }

    private static int getValidInput(Scanner input) {
        try {
            return input.nextInt();
        } catch (Exception e) {
            System.out.println("Input tidak sesuai. Masukkan angka.");
            input.nextLine(); // Consume the invalid input
            return getValidInput(input);
        }
    }

    public static boolean isValidMenuNumber(int number, int menuSize) {
        return number >= 1 && number <= menuSize;
    }

    public static List<Pesanan> addToPesanan(List<Pesanan> pesanan, Pesanan pesan) {
        return Stream.concat(pesanan.stream(), Stream.of(pesan))
                .collect(Collectors.toList());
    }

    public static int calculateTotalPembayaran(List<Pesanan> pesanan) {
        return pesanan.stream()
                .mapToInt(Pesanan::hitungTotal)
                .sum();
    }
}
