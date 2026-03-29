# LK04 - Kelas Inheritance, kelas Interface dan Kelas Final

## Kelompok
<table>
    <thead>
        <tr>
            <th>Nama</th>
            <th>NIM</th>
            <th>Kontribusi</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Candra Andika Putra</td>
            <td>255150200111035</td>
            <td>
                <ul>
                    <li>Merancang Class Diagram.</li>
                    <li>Mengimplementasikan interface.</li>
                    <li>Perbaikan logika kode class RekeningValas.</li>
                    <li>Menyusun laporan.</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>Haidar Nadhifa Putra</td>
            <td>255150201111031</td>
            <td>
                <ul>
                    <li>Menyusun awal class diagram.</li>
                    <li>Membuat hubungan antar class.</li>
                    <li>Menentukan beberapa method awal.</li>
                    <li>Membuat awal kode inheritance rekening.</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>Bagas Zakaria</td>
            <td>255150207111064</td>
            <td>
                <ul>
                    <li>Melanjutkan dan melengkapi pada Class Diagram.</li>
                    <li>Melakukan penyesuaian pada class RekeningValas.</li>
                    <li>Melakukan penyesuaian laporan.</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>Muhammad Hilmi Isnaeni</td>
            <td>255150201111026</td>
            <td>
                <ul>
                    <li>Menyesuaikan Class Diagram.</li>
                    <li>Mengimplementasikan class Main.</li>
                    <li>Melakukan penyesuaian pada final class ProtokolKeamanan.</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>Noval Zakky Ramadhan</td>
            <td>255150201111030</td>
            <td>
                <ul>
                    <li>Memperbaiki Class Diagram.</li>
                    <li>Melengkapi method-method pada setiap class.</li>
                    <li>Membuat class Main menjadi interaktif.</li>
                </ul>
            </td>
        </tr>
    </tbody>
</table>

## Deskripsi Soal

### Studi Kasus: Arsitektur Smart Banking Ecosystem
Bank "GakMauRugi" sedang membangun sistem transaksi yang sangat aman namun fleksibel. Anda diminta untuk mengimplementasikan rancangan berikut:
1. Hirarki Layanan (Interface Inheritance):
- Ada antarmuka dasar bernama Transaksi.
- Ada antarmuka TransaksiDigital yang mewarisi Transaksi.
- Ada antarmuka LayananInternasional yang mewarisi Transaksi.
- Ada antarmuka TransferGlobal yang mewarisi kedua antarmuka di atas (TransaksiDigital dan LayananInternasional). Ini adalah titik multiple inheritance.

2. Struktur Rekening (Class Inheritance):
- Sebuah superclass bernama Rekening yang menyimpan nomorRekening dan saldo.
- Sebuah subclass bernama RekeningValas (Valuta Asing) yang mewarisi Rekening dan mengimplementasikan antarmuka TransferGlobal.

3. Keamanan Enkripsi (Final Class & Variable):
- Setiap transaksi harus divalidasi oleh kelas ProtokolKeamanan yang bersifat final.
- Di dalam kelas tersebut, terdapat variabel final ID_SERVER yang tidak boleh diubah sejak
inisialisasi.

### Tugas:
Susunlah kode Bahasa pemrograman Java yang mencerminkan arsitektur di atas dengan ketentuan:
- Gunakan @Override dengan tepat.
- Tunjukkan bagaimana RekeningValas wajib mengimplementasikan seluruh kontrak dari hirarki interface.
- Pastikan ada mekanisme perlindungan data menggunakan final.

### Batas Pengumpulan:
- Tanggal 30 Maret 2026 Jam 17.00

### Ketentuan:
- Pekerjaan dilakukan secara kelompok. Jumlah anggota kelompok 3-5 Mhs