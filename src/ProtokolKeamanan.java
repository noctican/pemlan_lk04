package src;

public final class ProtokolKeamanan {
    // Atribut
    private final String ID_SERVER;

    // Constructor
    public ProtokolKeamanan(String ID) {
        this.ID_SERVER = ID;
    }

    // Method
    public boolean validasiTransaksi(RekeningValas rek) {
        // gak tau buat ngecek keamanannya atau saldonya, sementara gini aja
        System.out.println("Sedang melakukan validasi transaksi...");
        return true;
    }
}
