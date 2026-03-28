package src.dataInterface;

public interface TransaksiDigital extends Transaksi {
    public boolean verifikasiToken(String token);
    public String generateTokenOTP();
    public void otentikasiDigital(String pin);
}