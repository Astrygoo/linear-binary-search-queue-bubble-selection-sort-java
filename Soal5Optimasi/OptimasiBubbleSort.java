import java.util.Arrays;

public class OptimasiBubbleSort {
    record HasilUrut(
        int[] data,
        long perbandingan,
        long pertukaran,
        int putaran
    ) {}

    // Membuat data hampir terurut menurun dengan satu pasangan terakhir tertukar.
    private static int[] buatDataUji(int jumlahData) {
        int[] data = new int[jumlahData];

        for (int i = 0; i < jumlahData; i++) {
            data[i] = jumlahData - i;
        }

        int indeksTerakhir = jumlahData - 1;
        int sementara = data[indeksTerakhir - 1];
        data[indeksTerakhir - 1] = data[indeksTerakhir];
        data[indeksTerakhir] = sementara;

        return data;
    }

    // Bubble Sort biasa: selalu menjalankan semua putaran.
    private static HasilUrut bubbleSortBiasa(int[] dataAwal) {
        int[] data = dataAwal.clone();
        long perbandingan = 0;
        long pertukaran = 0;
        int putaran = 0;

        for (int i = 0; i < data.length - 1; i++) {
            putaran++;

            for (int j = 0; j < data.length - 1 - i; j++) {
                perbandingan++;

                if (data[j] < data[j + 1]) {
                    int sementara = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = sementara;
                    pertukaran++;
                }
            }
        }

        return new HasilUrut(data, perbandingan, pertukaran, putaran);
    }

    // Bubble Sort yang berhenti jika satu putaran tidak melakukan pertukaran.
    private static HasilUrut bubbleSortOptimasi(int[] dataAwal) {
        int[] data = dataAwal.clone();
        long perbandingan = 0;
        long pertukaran = 0;
        int putaran = 0;

        for (int i = 0; i < data.length - 1; i++) {
            putaran++;
            boolean adaPertukaran = false;

            for (int j = 0; j < data.length - 1 - i; j++) {
                perbandingan++;

                if (data[j] < data[j + 1]) {
                    int sementara = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = sementara;
                    pertukaran++;
                    adaPertukaran = true;
                }
            }

            // Jika tidak ada pertukaran, data sudah terurut.
            if (!adaPertukaran) {
                break;
            }
        }

        return new HasilUrut(data, perbandingan, pertukaran, putaran);
    }

    private static boolean sudahTerurutMenurun(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] < data[i + 1]) {
                return false;
            }
        }

        return true;
    }

    private static long hitungTotalLangkah(HasilUrut hasil) {
        // Total langkah di sini menghitung perbandingan + pertukaran.
        return hasil.perbandingan() + hasil.pertukaran();
    }

    private static void tampilkanHasil(String nama, HasilUrut hasil) {
        System.out.printf(
            "%s: perbandingan=%d, pertukaran=%d, total langkah=%d, putaran=%d, hasil terurut=%s%n",
            nama,
            hasil.perbandingan(),
            hasil.pertukaran(),
            hitungTotalLangkah(hasil),
            hasil.putaran(),
            sudahTerurutMenurun(hasil.data()) ? "Ya" : "Tidak"
        );
    }

    public static void main(String[] args) {
        System.out.println("SOAL 5 - UJI DAN OPTIMASI BUBBLE SORT");
        System.out.println("Algoritma yang diuji berasal dari soal 4: Bubble Sort.");
        System.out.println("Data hampir terurut menurun, dengan dua angka terakhir tertukar.");
        System.out.println("Total langkah = jumlah perbandingan + jumlah pertukaran.");
        System.out.println("Perhitungan tidak memasukkan operasi kontrol perulangan.");

        int[] ukuranData = {10, 50, 100};

        for (int jumlahData : ukuranData) {
            int[] dataAwal = buatDataUji(jumlahData);

            HasilUrut hasilBiasa = bubbleSortBiasa(dataAwal);
            HasilUrut hasilOptimasi = bubbleSortOptimasi(dataAwal);

            System.out.printf("%n=== Pengujian %d data ===%n", jumlahData);

            if (jumlahData == 10) {
                System.out.println("Data awal: " + Arrays.toString(dataAwal));
                System.out.println(
                    "Hasil urut: " + Arrays.toString(hasilOptimasi.data())
                );
            }

            tampilkanHasil("Sebelum optimasi", hasilBiasa);
            tampilkanHasil("Sesudah optimasi", hasilOptimasi);

            System.out.println(
                "Hasil kedua cara sama: "
                    + Arrays.equals(hasilBiasa.data(), hasilOptimasi.data())
            );
        }

        System.out.println("\nBagian yang paling banyak bekerja adalah perbandingan");
        System.out.println("nilai di dalam perulangan bersarang.");
        System.out.println("Optimasi: hentikan proses jika satu putaran tidak menukar data.");
        System.out.println("Kompleksitas waktu worst-case tetap O(n^2).");
        System.out.println("Pada data yang sudah terurut, optimasi dapat mencapai O(n).");
        System.out.println("Kedua versi menggunakan ruang tambahan O(1).");
    }
}