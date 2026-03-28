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
}
