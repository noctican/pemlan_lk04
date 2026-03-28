package src.dataInterface;

public interface Transaksi {
    public void prosesTransaksi(double jumlah, String pin);

    public double getJumlah();
}