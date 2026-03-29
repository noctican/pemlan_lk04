package src;

public final class ProtokolKeamanan {
    // Atribut
    private final String ID_SERVER;
    private static final String ENKRIPSI_ALGORITMA = "AES-256";
    private String currentOTP;
    private long otpExpiryTime;

    // Constructor
    public ProtokolKeamanan(String ID) {
        this.ID_SERVER = ID;
        this.currentOTP = null;
    }

    // Method
    public String generateOTP() {
        int otp = (int) (Math.random() * 900000) + 100000;
        this.currentOTP = String.valueOf(otp);
        this.otpExpiryTime = System.currentTimeMillis() + (5 * 60 * 1000);
        System.out.println("OTP telah dikirim: " + currentOTP);
        return currentOTP;
    }

    public boolean verifikasiOTP(String token) {
        if (currentOTP == null) {
            System.out.println("Tidak ada OTP aktif. Silahkan generate OTP terlebih dahulu.");
            return false;
        }

        if (System.currentTimeMillis() > otpExpiryTime) {
            System.out.println("OTP telah kadaluarsa! (lebih dari 5 menit)");
            currentOTP = null;
            return false;
        }

        if (currentOTP.equals(token)) {
            System.out.println("Verifikasi OTP berhasil!");
            currentOTP = null;
            return true;
        } else {
            System.out.println("Verifikasi OTP gagal! Token yang dimasukkan salah.");
            return false;
        }
    }

    private boolean validasiPIN(RekeningValas rek, String pin) {
        System.out.println("Memeriksa PIN...");
        return rek.cekPIN(pin);
    }

    public boolean validasiTransaksi(RekeningValas rek, String pin, String tokenOTP) {
        System.out.println("\n=== PROTOKOL KEAMANAN BANK ===");
        System.out.println("Server ID: " + ID_SERVER);
        System.out.println("Algoritma Enkripsi: " + ENKRIPSI_ALGORITMA);
        System.out.println("Rekening: " + rek.getNomorRekening());
        System.out.println("-------------------------------------");

        if (!validasiPIN(rek, pin)) {
            System.out.println("VALIDASI GAGAL: PIN salah!");
            return false;
        }

        if (!verifikasiOTP(tokenOTP)) {
            System.out.println("VALIDASI GAGAL: OTP tidak valid!");
            return false;
        }

        System.out.println("SEMUA VALIDASI BERHASIL!");
        System.out.println("Transaksi dapat diproses.\n");
        return true;
    }

    public boolean validasiTransaksi(RekeningValas rek, String pin) {
        System.out.println("\n=== PROTOKOL KEAMANAN BANK ===");
        System.out.println("Server ID: " + ID_SERVER);
        System.out.println("-------------------------------------");

        if (validasiPIN(rek, pin)) {
            System.out.println("VALIDASI BERHASIL!\n");
            return true;
        }

        System.out.println("VALIDASI GAGAL: PIN salah!\n");
        return false;
    }

    public void logAktivitas(String aktivitas) {
        System.out.println("[LOG " + System.currentTimeMillis() + "] " + aktivitas);
    }

    public String getID_SERVER() {
        return ID_SERVER;
    }
}
