import java.text.NumberFormat;
import java.util.Locale;

public class TotalBelanja {
    // Sepuluh data transaksi yang sama dipakai oleh kedua pendekatan.
    private static final int[] HARGA = {
        12000, 18000, 25000, 9000, 32000,
        15000, 22000, 11000, 27000, 14000
    };

    private static int jumlahPanggilanRekursif = 0;
    private static int jumlahPenjumlahanRekursif = 0;

    private static final NumberFormat FORMAT_RUPIAH =
        NumberFormat.getIntegerInstance(Locale.forLanguageTag("id-ID"));

    private static String rupiah(long nilai) {
        return "Rp" + FORMAT_RUPIAH.format(nilai);
    }

    // Menjumlahkan transaksi dari indeks tertentu sampai akhir array.
    private static long hitungRekursif(int[] harga, int indeks) {
        jumlahPanggilanRekursif++;

        // Kondisi berhenti: semua transaksi sudah diproses.
        if (indeks == harga.length) {
            System.out.printf(
                "Panggilan indeks %d: semua transaksi selesai, nilai = 0%n",
                indeks
            );
            return 0;
        }

        System.out.printf(
            "Panggilan indeks %d: transaksi %d = %s%n",
            indeks, indeks + 1, rupiah(harga[indeks])
        );

        // Hitung total transaksi setelah indeks saat ini.
        long totalSisa = hitungRekursif(harga, indeks + 1);

        // Saat fungsi kembali, tambahkan harga saat ini ke total sisanya.
        long hasil = harga[indeks] + totalSisa;
        jumlahPenjumlahanRekursif++;

        System.out.printf(
            "Kembali ke transaksi %d: %s + total transaksi berikutnya %s = %s%n",
            indeks + 1,
            rupiah(harga[indeks]),
            rupiah(totalSisa),
            rupiah(hasil)
        );

        return hasil;
    }

    public static void main(String[] args) {
        System.out.println("SOAL 3 - PERBANDINGAN TOTAL BELANJA");
        System.out.println("Data transaksi yang dipakai oleh kedua cara:");

        for (int i = 0; i < HARGA.length; i++) {
            System.out.printf(
                "Transaksi %d: %s%n",
                i + 1, rupiah(HARGA[i])
            );
        }

        // Cara pertama: iteratif menggunakan perulangan.
        System.out.println("\nA. PERHITUNGAN ITERATIF");
        long totalIteratif = 0;
        int jumlahPenjumlahanIteratif = 0;

        for (int i = 0; i < HARGA.length; i++) {
            long totalSebelum = totalIteratif;
            totalIteratif += HARGA[i];
            jumlahPenjumlahanIteratif++;

            System.out.printf(
                "Langkah %d: %s + %s = %s%n",
                i + 1,
                rupiah(totalSebelum),
                rupiah(HARGA[i]),
                rupiah(totalIteratif)
            );
        }

        // Cara kedua: rekursif menggunakan pemanggilan fungsi.
        System.out.println("\nB. PERHITUNGAN REKURSIF");
        long totalRekursif = hitungRekursif(HARGA, 0);

        // Membandingkan hasil dan hitungan dari kedua cara.
        System.out.println("\nC. PERBANDINGAN");
        System.out.printf(
            "%-14s | %-14s | %-16s | %-16s%n",
            "Pendekatan", "Total", "Penjumlahan", "Panggilan fungsi"
        );
        System.out.println(
            "---------------+----------------+------------------+-----------------"
        );
        System.out.printf(
            "%-14s | %-14s | %-16d | %-16s%n",
            "Iteratif",
            rupiah(totalIteratif),
            jumlahPenjumlahanIteratif,
            "Tidak ada"
        );
        System.out.printf(
            "%-14s | %-14s | %-16d | %-16d%n",
            "Rekursif",
            rupiah(totalRekursif),
            jumlahPenjumlahanRekursif,
            jumlahPanggilanRekursif
        );

        System.out.printf(
            "%nSelisih hasil: %s%n",
            rupiah(Math.abs(totalIteratif - totalRekursif))
        );
        System.out.println(
            "Hasil kedua pendekatan sama: "
                + (totalIteratif == totalRekursif ? "Ya" : "Tidak")
        );
        System.out.println(
            "Analisis teoritis: keduanya O(n) waktu; iteratif O(1) ruang, "
                + "rekursif O(n) ruang stack."
        );
    }
}