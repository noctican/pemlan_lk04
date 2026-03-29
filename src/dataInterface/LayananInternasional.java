package src.dataInterface;

public interface LayananInternasional extends Transaksi {
    public void konversiMataUang(String mataTujuan);
    public double hitungBiayaKurs(double jumlah, String mataUangAsing);
    public boolean validasiNegaraTujuan(String kodeNegara);
}