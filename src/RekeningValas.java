package src;

import src.dataInterface.*;

public class RekeningValas extends Rekening implements TransferGlobal {
    // Atribut
    private String mataUang;
    private ProtokolKeamanan versiProtokol;

    // Constructor
    public RekeningValas(String mataUang, String nomorRekening, double saldo, ProtokolKeamanan protokol, String pin) {
        super(nomorRekening, saldo, pin);
        this.mataUang = mataUang;
        this.versiProtokol = protokol;
    }

    // Method
    @Override
    public void prosesTransaksi(double jumlah) {
        if (versiProtokol.validasiTransaksi(this)) {
            if (getSaldo() >= jumlah) {
                System.out.println("Transaksi berhasil!");
                System.out.println("Saldo anda sekarang : " + (getSaldo() - jumlah));
            } else {
                System.out.println("Transaksi gagal! Saldo tidak mencukupi.");
            }
        } else {
            System.out.println("Transaksi gagal!");
        }
    }

    @Override
    public void verifikasiToken() {
    }

    @Override
    public void konversiMataUang(String mataTujuan) {
    }

    @Override
    public void transferGlobal(String rekTujuan, String mataUang, double jumlah) {
    }

    @Override
    public double getJumlah() {
        return getSaldo();
    }
}
