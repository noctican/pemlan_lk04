package src;

public class Rekening {
    // Atribut
    private String nomorRekening;
    private double saldo;
    private String pin;

    // Constructor
    public Rekening(String nomorRekening, double saldo, String pin){
        this.nomorRekening = nomorRekening;
        this.saldo = saldo;
        this.pin = pin;
    }

    // Method
    public void tampilkanInfo(){
        System.out.println("Nomor Rekening Anda : " + nomorRekening);
        System.out.println("Saldo anda          : " + saldo);
    }

    public double getSaldo(){
        return this.saldo;
    }

    public String getNomorRekening() {
        return this.nomorRekening;
    }

    public boolean cekPIN(String pinInput) {
        return this.pin.equals(pinInput);
    }

    public boolean withdraw(double jumlah) {
        if (jumlah > 0 && saldo >= jumlah) {
            saldo -= jumlah;
            System.out.println("Penarikan sebesar " + jumlah + " berhasil.");
            System.out.println("Sisa saldo: " + saldo);
            return true;
        }
        System.out.println("Penarikan gagal! Saldo tidak mencukupi.");
        return false;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    protected String getPin(){
        return pin;
    }
}
