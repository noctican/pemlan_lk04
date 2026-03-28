package src;

public class BankGakMauRugi {
    public static void main(String[] args) {
        // tambahin scanner aja klo misal mau dibikin interaktif
        System.out.println("=== Bank Gak Mau Rugi ===");

        ProtokolKeamanan protokol = new ProtokolKeamanan("12345"); // final ID_SERVERnya bebas
        RekeningValas rekening = new RekeningValas("USD", "123456789", 1000, protokol, "1234");

        // Tampilkan Info Rekening
        System.out.println("\n--- Informasi Rekening ---");
        rekening.tampilkanInfo();

        // Proses Transaksi
        System.out.println("\n--- Melakukan Transaksi ---");
        rekening.prosesTransaksi(700);
    }
}
