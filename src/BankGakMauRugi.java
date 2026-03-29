package src;

import java.util.Scanner;

public class BankGakMauRugi {
    private static Scanner scanner = new Scanner(System.in);
    private static ProtokolKeamanan protokol;
    private static RekeningValas rekening;
    private static String currentOTP = null;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("     BANK GAK MAU RUGI");
        System.out.println("     Smart Banking Ecosystem");
        System.out.println("========================================\n");

        // Inisialisasi Protokol Keamanan dengan ID Server final
        protokol = new ProtokolKeamanan("SRV-GMR-2026-001");

        // Inisialisasi Rekening Valas
        rekening = new RekeningValas(
            "IDR",
            "IDR-2026-001",
            100000000,
            protokol,
            "123456",
            "BRINIDJA"
        );

        // Tampilkan Informasi Awal Rekening
        System.out.println("=== INFORMASI REKENING AWAL ===");
        rekening.tampilkanInfo();
        System.out.println("Mata Uang: " + rekening.getMataUang());
        System.out.println("Kode SWIFT: " + rekening.getKodeSWIFT());
        System.out.println();

        // Menu Utama
        int pilihan;
        do {
            tampilkanMenu();
            System.out.print("Pilih transaksi (0-8): ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    transaksiDenganOTP();
                    break;
                case 2:
                    transaksiTanpaOTP();
                    break;
                case 3:
                    transferGlobal();
                    break;
                case 4:
                    verifikasiToken();
                    break;
                case 5:
                    generateTokenOTP();
                    break;
                case 6:
                    otentikasiDigital();
                    break;
                case 7:
                    konversiMataUang();
                    break;
                case 8:
                    validasiNegaraTujuan();
                    break;
                case 0:
                    System.out.println("\nTerima kasih telah menggunakan Bank Gak Mau Rugi!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
            System.out.println();
        } while (pilihan != 0);

        // Bukti Final Class & Variable
        System.out.println("\n=== BUKTI FINAL CLASS & VARIABLE ===");
        System.out.println("Class ProtokolKeamanan: final class (tidak dapat diwariskan)");
        System.out.println("ID_SERVER (final variable): " + protokol.getID_SERVER());
        System.out.println("ID_SERVER bersifat final dan tidak dapat diubah setelah inisialisasi");

        protokol.logAktivitas("Akses ke rekening " + rekening.getNomorRekening());

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("=== MENU TRANSAKSI ===");
        System.out.println("1. Transaksi dengan OTP (prosesTransaksi + OTP)");
        System.out.println("2. Transaksi tanpa OTP (prosesTransaksi)");
        System.out.println("3. Transfer Global (transferGlobal)");
        System.out.println("4. Verifikasi Token (verifikasiToken)");
        System.out.println("5. Generate Token OTP (generateTokenOTP)");
        System.out.println("6. Otentikasi Digital (otentikasiDigital)");
        System.out.println("7. Konversi Mata Uang (konversiMataUang)");
        System.out.println("8. Validasi Negara Tujuan (validasiNegaraTujuan)");
        System.out.println("0. Keluar");
    }

    // Method untuk transaksi dengan OTP (menggunakan prosesTransaksi dengan parameter OTP)
    private static void transaksiDenganOTP() {
        System.out.println("\n=== TRANSAKSI DENGAN OTP ===");
        
        System.out.print("Masukkan PIN Anda: ");
        String pin = scanner.nextLine();

        System.out.print("Masukkan jumlah transaksi: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine();
        
        // Generate OTP melalui protokol
        System.out.println("\nMengirim OTP...");
        currentOTP = protokol.generateOTP();
        
        System.out.print("Masukkan OTP yang diterima: ");
        String otpInput = scanner.nextLine();
        
        // Validasi
        rekening.prosesTransaksi(jumlah, pin, otpInput);
    }

    // Method untuk transaksi tanpa OTP (menggunakan prosesTransaksi tanpa parameter OTP)
    private static void transaksiTanpaOTP() {
        System.out.println("\n=== TRANSAKSI TANPA OTP ===");
        
        System.out.print("Masukkan PIN Anda: ");
        String pin = scanner.nextLine();
        
        // Validasi tanpa OTP
        if (protokol.validasiTransaksi(rekening, pin)) {
            System.out.print("Masukkan jumlah transaksi: ");
            double jumlah = scanner.nextDouble();
            scanner.nextLine();
            
            // Panggil prosesTransaksi tanpa OTP
            rekening.prosesTransaksi(jumlah, pin);
        } else {
            System.out.println("Transaksi dibatalkan karena PIN salah!");
        }
    }

    // Method untuk transfer global (menggunakan method transferGlobal)
    private static void transferGlobal() {
        System.out.println("\n=== TRANSFER GLOBAL ===");
        
        System.out.print("Masukkan PIN Anda: ");
        String pin = scanner.nextLine();
        
        // Validasi melalui ProtokolKeamanan
        if (protokol.validasiTransaksi(rekening, pin)) {
            System.out.print("Masukkan nomor rekening tujuan (format: US-xxxxx / SG-xxxxx / JP-xxxxx): ");
            String rekTujuan = scanner.nextLine();
            
            System.out.print("Masukkan mata uang tujuan (USD/EUR/JPY/SGD/GBP): ");
            String mataUangTujuan = scanner.nextLine();
            
            System.out.print("Masukkan jumlah yang akan ditransfer: ");
            double jumlahTransfer = scanner.nextDouble();
            scanner.nextLine();
            
            // Panggil method transferGlobal
            rekening.transferGlobal(rekTujuan, mataUangTujuan, jumlahTransfer);
        } else {
            System.out.println("Transfer dibatalkan karena validasi gagal!");
        }
    }

    // Method untuk verifikasi token (menggunakan method verifikasiToken)
    private static void verifikasiToken() {
        System.out.println("\n=== VERIFIKASI TOKEN ===");
        
        if (currentOTP == null) {
            System.out.println("Belum ada OTP yang digenerate. Silahkan generate OTP terlebih dahulu.");
            System.out.print("Apakah ingin generate OTP sekarang? (y/n): ");
            String jawab = scanner.nextLine();
            if (jawab.equalsIgnoreCase("y")) {
                currentOTP = protokol.generateOTP();
            } else {
                return;
            }
        }
        
        System.out.print("Masukkan token OTP untuk diverifikasi: ");
        String token = scanner.nextLine();
        
        // Reset currentOTP jika verifikasi berhasil
        if (rekening.verifikasiToken(token)) {
            currentOTP = null;
        }
    }

    // Method untuk generate token OTP (menggunakan method generateTokenOTP)
    private static void generateTokenOTP() {
        System.out.println("\n=== GENERATE TOKEN OTP ===");
        currentOTP = rekening.generateTokenOTP();
        System.out.println("Simpan OTP Anda untuk verifikasi selanjutnya.");
    }

    // Method untuk otentikasi digital (menggunakan method otentikasiDigital)
    private static void otentikasiDigital() {
        System.out.println("\n=== OTENTIKASI DIGITAL ===");
        
        System.out.print("Masukkan PIN untuk otentikasi digital: ");
        String pin = scanner.nextLine();
        
        // Panggil method otentikasiDigital
        rekening.otentikasiDigital(pin);
    }

    // Method untuk konversi mata uang (menggunakan method konversiMataUang)
    private static void konversiMataUang() {
        System.out.println("\n=== KONVERSI MATA UANG ===");
        
        System.out.print("Masukkan mata uang tujuan (USD/EUR/JPY/SGD/GBP): ");
        String mataTujuan = scanner.nextLine();
        
        System.out.print("Masukkan jumlah yang akan dikonversi: ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Masukkan mata uang asing: ");
        String mataUangAsing = scanner.nextLine();
        
        // Panggil method konversiMataUang dan hitungBiayaKurs
        rekening.konversiMataUang(mataTujuan);
        double totalBiaya = rekening.hitungBiayaKurs(jumlah, mataUangAsing);
        System.out.println("Total biaya yang diperlukan: " + totalBiaya + " " + rekening.getMataUang());
    }

    // Method untuk validasi negara tujuan (menggunakan method validasiNegaraTujuan)
    private static void validasiNegaraTujuan() {
        System.out.println("\n=== VALIDASI NEGARA TUJUAN ===");
        
        System.out.print("Masukkan kode negara (US/SG/JP/DE/GB/ID): ");
        String kodeNegara = scanner.nextLine().toUpperCase();
        
        // Panggil method validasiNegaraTujuan
        boolean valid = rekening.validasiNegaraTujuan(kodeNegara);
        
        if (valid) {
            System.out.println("Negara " + kodeNegara + " diizinkan untuk transaksi internasional.");
        } else {
            System.out.println("Negara " + kodeNegara + " tidak diizinkan untuk transaksi internasional.");
        }
    }
}