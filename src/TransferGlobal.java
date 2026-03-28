package src;

interface TranferGlobal extends TransaksiDigital, LayananInternasional {
    public void transferGlobal(String rekTujuan, String mataUang, double jumlah);
}