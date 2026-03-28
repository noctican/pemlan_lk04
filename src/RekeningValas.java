package src;

import src.dataInterface.*;
import java.util.Scanner;

public class RekeningValas extends Rekening implements TransferGlobal {
    // Atribut
    private String mataUang;
    private ProtokolKeamanan versiProtokol;
    private String kodeSWIFT;
    private static final double BIAYA_ADMIN_INTERNASIONAL = 0.02;

    // Constructor
    public RekeningValas(String mataUang, String nomorRekening, double saldo, ProtokolKeamanan protokol, String pin, String kodeSWIFT) {
        super(nomorRekening, saldo, pin);
        this.mataUang = mataUang;
        this.versiProtokol = protokol;
        this.kodeSWIFT = kodeSWIFT;
    }

    // Method
    @Override
    public void prosesTransaksi(double jumlah, String pin) {
         System.out.println("\nMEMPROSES TRANSAKSI SEBESAR " + jumlah + " " + mataUang);
        
        if (versiProtokol.validasiTransaksi(this, getPINForValidation(pin))) {
            if (withdraw(jumlah)) {
                System.out.println("Transaksi berhasil diproses!");
            } else {
                System.out.println("Transaksi gagal! Saldo tidak mencukupi.");
            }
        } else {
            System.out.println("Transaksi gagal! Validasi keamanan tidak lolos.");
        }
    }

    public void prosesTransaksi(double jumlah, String pin, String tokenOTP) {
        System.out.println("\nMEMPROSES TRANSAKSI SEBESAR " + jumlah + " " + mataUang);
        
        if (versiProtokol.validasiTransaksi(this, getPINForValidation(pin), tokenOTP)) {
            if (withdraw(jumlah)) {
                System.out.println("Transaksi berhasil diproses!");
            } else {
                System.out.println("Transaksi gagal! Saldo tidak mencukupi.");
            }
        } else {
            System.out.println("Transaksi gagal! Validasi keamanan tidak lolos.");
        }
    }

    @Override
    public boolean verifikasiToken(String token) {
        System.out.println("Memverifikasi token OTP...");
        if (versiProtokol.verifikasiOTP(token)) {
            System.out.println("Token OTP valid!");
            return true;
        } else {
            System.out.println("Token OTP tidak valid!");
            return false;
        }
    }

    @Override
    public String generateTokenOTP() {
        System.out.println("Mengirim OTP ke nomor terdaftar...");
        return versiProtokol.generateOTP();
    }

    @Override
    public void otentikasiDigital(String pin) {
        System.out.println("Melakukan otentikasi digital...");
        if (cekPIN(pin)) {
            System.out.println("Otentikasi digital berhasil!");
        } else {
            System.out.println("Otentikasi digital gagal! PIN salah.");
        }
    }

    @Override
    public void konversiMataUang(String mataTujuan) {
        System.out.println("Mengkonversi " + mataUang + " ke " + mataTujuan);
        double kurs = getKurs(mataTujuan);
        System.out.println("Kurs: 1 " + mataUang + " = " + kurs + " " + mataTujuan);
    }

    @Override
    public double hitungBiayaKurs(double jumlah, String mataUangAsing) {
        double kurs = getKurs(mataUangAsing);
        double biayaAdmin = jumlah * BIAYA_ADMIN_INTERNASIONAL;
        double totalBiaya = (jumlah * kurs) + biayaAdmin;
        System.out.println("Kurs " + mataUangAsing + " ke " + mataUang + ": 1 " + mataUangAsing + " = " + kurs + " " + mataUang);
        System.out.println("Biaya admin internasional: " + biayaAdmin + " " + mataUang);
        return totalBiaya;
    }

    @Override
    public boolean validasiNegaraTujuan(String kodeNegara) {
        String[] negaraDiizinkan = {"US", "SG", "JP", "DE", "GB"};
        for (String negara : negaraDiizinkan) {
            if (negara.equals(kodeNegara)) {
                System.out.println("Negara " + kodeNegara + " valid untuk transaksi internasional.");
                return true;
            }
        }
        System.out.println("Negara " + kodeNegara + " tidak diizinkan untuk transaksi internasional.");
        return false;
    }

    @Override
    public void transferGlobal(String rekTujuan, String mataUangTujuan, double jumlah) {
        System.out.println("\n=== TRANSFER GLOBAL ===");
        System.out.println("Dari rekening: " + getNomorRekening() + " (" + mataUang + ")");
        System.out.println("Ke rekening tujuan: " + rekTujuan);
        System.out.println("Mata uang tujuan: " + mataUangTujuan);
        
        String kodeNegara = ekstrakKodeNegara(rekTujuan);
        
        if (validasiNegaraTujuan(kodeNegara)) {
            String otp = generateTokenOTP();
            System.out.println("Untuk melanjutkan transfer, masukkan OTP yang telah dikirim.");
            Scanner inp = new Scanner(System.in);
            System.out.print("Masukkan OTP : ");
            String inpOTP = inp.next();
            
            if (versiProtokol.verifikasiOTP(inpOTP)) {
                double totalBiaya = hitungBiayaKurs(jumlah, mataUangTujuan);
                
                if (getSaldo() >= totalBiaya) {
                    withdraw(totalBiaya);
                    System.out.println("\nTransfer berhasil!");
                    System.out.println("Jumlah yang diterima penerima: " + jumlah + " " + mataUangTujuan);
                    System.out.println("Total biaya yang dibebankan: " + totalBiaya + " " + mataUang);
                    System.out.println("Sisa saldo: " + getSaldo() + " " + mataUang);
                } else {
                    System.out.println("\nTransfer gagal! Saldo tidak mencukupi.");
                    System.out.println("Saldo saat ini: " + getSaldo() + " " + mataUang);
                    System.out.println("Total biaya yang diperlukan: " + totalBiaya + " " + mataUang);
                }
            } else {
                System.out.println("Transfer gagal! Verifikasi OTP tidak berhasil.");
            }
        } else {
            System.out.println("Transfer gagal! Negara tujuan tidak diizinkan.");
        }
        System.out.println("=======================\n");
    }

    @Override
    public String getKodeSWIFT() {
        return kodeSWIFT;
    }

    private String getPINForValidation(String pin) {
        if (pin.equals(getPin())){
            return getPin();
        }
        else {
            return null;
        }
    }

    private double getKurs(String mataUangTujuan) {
        switch (mataUangTujuan) {
            case "USD": return 15000.0;
            case "EUR": return 16500.0;
            case "JPY": return 135.0;
            case "SGD": return 11000.0;
            case "GBP": return 19000.0;
            default: return 1.0;
        }
    }

    private String ekstrakKodeNegara(String rekTujuan) {
        if (rekTujuan.startsWith("US-")) return "US";
        if (rekTujuan.startsWith("SG-")) return "SG";
        if (rekTujuan.startsWith("JP-")) return "JP";
        if (rekTujuan.startsWith("DE-")) return "DE";
        if (rekTujuan.startsWith("GB-")) return "GB";
        return "ID";
    }

    public String getMataUang() {
        return mataUang;
    }

    @Override
    public double getJumlah() {
        return getSaldo();
    }
}
