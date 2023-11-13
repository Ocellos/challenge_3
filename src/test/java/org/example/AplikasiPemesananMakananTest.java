package org.example;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class AplikasiPemesananMakananTest extends TestCase {
    @Test
    public void testCalculateTotalPembayaran() {
        List<MenuMakanan> daftarMenu = List.of(
                new MenuMakanan("Nasi Goreng", 15000),
                new MenuMakanan("Mie Ayam", 12000),
                new MenuMakanan("Soto Ayam", 10000)
        );

        List<Pesanan> pesanan = List.of(
                new Pesanan(daftarMenu.get(0), 2),
                new Pesanan(daftarMenu.get(1), 3)
        );

        int totalPembayaran = AplikasiPemesananMakanan.calculateTotalPembayaran(pesanan);

        assertEquals(2 * 15000 + 3 * 12000, totalPembayaran);
    }

    @Test
    public void testAddToPesanan() {
        List<Pesanan> pesanan = List.of(
                new Pesanan(new MenuMakanan("Nasi Goreng", 15000), 2),
                new Pesanan(new MenuMakanan("Mie Ayam", 12000), 3)
        );

        Pesanan newPesanan = new Pesanan(new MenuMakanan("Soto Ayam", 10000), 1);

        List<Pesanan> updatedPesanan = AplikasiPemesananMakanan.addToPesanan(pesanan, newPesanan);

        assertEquals(3, updatedPesanan.size());
        assertEquals(newPesanan, updatedPesanan.get(2));
    }

    @Test
    public void testIsValidMenuNumber() {
        int validNumber = 2;
        int invalidNumber1 = 0;
        int invalidNumber2 = 4;

        List<MenuMakanan> daftarMenu = List.of(
                new MenuMakanan("Nasi Goreng", 15000),
                new MenuMakanan("Mie Ayam", 12000),
                new MenuMakanan("Soto Ayam", 10000)
        );

        boolean isValidValidNumber = AplikasiPemesananMakanan.isValidMenuNumber(validNumber, daftarMenu.size());
        boolean isValidInvalidNumber1 = AplikasiPemesananMakanan.isValidMenuNumber(invalidNumber1, daftarMenu.size());
        boolean isValidInvalidNumber2 = AplikasiPemesananMakanan.isValidMenuNumber(invalidNumber2, daftarMenu.size());

        assertEquals(true, isValidValidNumber);
        assertEquals(false, isValidInvalidNumber1);
        assertEquals(false, isValidInvalidNumber2);
    }

    // Add more tests as needed

    @Test
    public void testMainMethod() {
        // This is an example of how you can test the main method using input redirection.
        // Note: This is just a basic example, and you may need to adapt it based on your specific needs.

        String simulatedInput = "1\n3\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Redirect output to capture the printed messages
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the main method
        AplikasiPemesananMakanan.main(new String[]{});

        // Reset System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

        // Perform assertions based on the output
        String output = outputStream.toString().trim();
        assertTrue(output.contains("=== Menu Utama ==="));
        assertTrue(output.contains("=== Konfirmasi dan Pembayaran ==="));
    }
}