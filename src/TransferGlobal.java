interface TransaksiGlobal extends TransaksiDigital, LayananInternasional {
    public void transferGlobal(String rekTujuan, String mataUang, double jumlah);
}