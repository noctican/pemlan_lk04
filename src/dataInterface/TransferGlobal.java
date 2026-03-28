package src.dataInterface;

public interface TransferGlobal extends TransaksiDigital, LayananInternasional {
    public void transferGlobal(String rekTujuan, String mataUang, double jumlah);
}