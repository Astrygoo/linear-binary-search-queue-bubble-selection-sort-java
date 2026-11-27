import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PencarianMahasiswa {
    record Mahasiswa(String nim, String nama) {}
    record Hasil(int indeks, int langkah) {}
    record Ringkasan(String nim, String nama, int langkahLinear, int langkahBiner) {}

    // Memeriksa NIM dari data pertama secara berurutan.
    private static Hasil linear(List<Mahasiswa> data, String target) {
        int langkah = 0;

        System.out.println("  Linear Search memeriksa data dari awal:");

        for (int i = 0; i < data.size(); i++) {
            Mahasiswa mahasiswa = data.get(i);
            langkah++;

            if (mahasiswa.nim().equals(target)) {
                System.out.printf(
                    "    Langkah %d: cek %s -> ditemukan (%s).%n",
                    langkah, mahasiswa.nim(), mahasiswa.nama()
                );
                return new Hasil(i, langkah);
            }

            System.out.printf(
                "    Langkah %d: cek %s -> belum cocok.%n",
                langkah, mahasiswa.nim()
            );
        }

        System.out.printf(
            "    NIM %s tidak ditemukan setelah %d pemeriksaan.%n",
            target, langkah
        );
        return new Hasil(-1, langkah);
    }

    // Memeriksa NIM tengah, lalu mempersempit pencarian ke separuh data.
    private static Hasil biner(List<Mahasiswa> data, String target) {
        int kiri = 0;
        int kanan = data.size() - 1;
        int langkah = 0;

        System.out.println(
            "  Binary Search memeriksa nilai tengah pada rentang yang makin kecil:"
        );

        while (kiri <= kanan) {
            int tengah = kiri + (kanan - kiri) / 2;
            Mahasiswa mahasiswa = data.get(tengah);
            langkah++;

            int hasilBanding = mahasiswa.nim().compareTo(target);

            if (hasilBanding == 0) {
                System.out.printf(
                    "    Langkah %d: indeks %d, cek %s -> ditemukan (%s).%n",
                    langkah, tengah, mahasiswa.nim(), mahasiswa.nama()
                );
                return new Hasil(tengah, langkah);
            }

            if (hasilBanding < 0) {
                System.out.printf(
                    "    Langkah %d: indeks %d, cek %s -> target lebih besar, lanjut ke separuh kanan.%n",
                    langkah, tengah, mahasiswa.nim()
                );
                kiri = tengah + 1;
            } else {
                System.out.printf(
                    "    Langkah %d: indeks %d, cek %s -> target lebih kecil, lanjut ke separuh kiri.%n",
                    langkah, tengah, mahasiswa.nim()
                );
                kanan = tengah - 1;
            }
        }

        System.out.printf(
            "    NIM %s tidak ditemukan setelah %d pemeriksaan.%n",
            target, langkah
        );
        return new Hasil(-1, langkah);
    }

    public static void main(String[] args) {
        List<Mahasiswa> data = new ArrayList<>(List.of(
            new Mahasiswa("25552011001", "Alya Putri"),
            new Mahasiswa("25552011002", "Bima Pratama"),
            new Mahasiswa("25552011003", "Citra Lestari"),
            new Mahasiswa("25552011004", "Danu Saputra"),
            new Mahasiswa("25552011005", "Eka Maharani"),
            new Mahasiswa("25552011006", "Farhan Akbar"),
            new Mahasiswa("25552011007", "Gita Permata"),
            new Mahasiswa("25552011008", "Hadi Wijaya"),
            new Mahasiswa("25552011009", "Intan Sari"),
            new Mahasiswa("25552011010", "Joko Santoso")
        ));

        // Binary Search hanya dapat dipakai jika data terurut berdasarkan NIM.
        data.sort(Comparator.comparing(Mahasiswa::nim));

        System.out.println("SOAL 1 - PENCARIAN DATA MAHASISWA");
        System.out.println("Data uji (10 mahasiswa, urut berdasarkan NIM):");

        for (Mahasiswa mahasiswa : data) {
            System.out.println("  " + mahasiswa.nim() + " - " + mahasiswa.nama());
        }

        System.out.println("Masukkan tiga NIM berbeda untuk dibandingkan.");

        List<Ringkasan> ringkasan = new ArrayList<>();
        Set<String> nimYangSudahDipakai = new HashSet<>();

        try (Scanner scanner = new Scanner(System.in)) {
            for (int nomor = 1; nomor <= 3; nomor++) {
                System.out.printf("NIM pencarian ke-%d: ", nomor);
                String target = scanner.nextLine().trim();

                while (nimYangSudahDipakai.contains(target)) {
                    System.out.println("NIM harus berbeda. Masukkan NIM lain:");
                    System.out.printf("NIM pencarian ke-%d: ", nomor);
                    target = scanner.nextLine().trim();
                }

                nimYangSudahDipakai.add(target);

                System.out.printf("%nUJI %d - NIM %s%n", nomor, target);

                Hasil hasilLinear = linear(data, target);
                Hasil hasilBiner = biner(data, target);

                String nama = hasilLinear.indeks() >= 0
                    ? data.get(hasilLinear.indeks()).nama()
                    : "tidak ditemukan";

                ringkasan.add(new Ringkasan(
                    target,
                    nama,
                    hasilLinear.langkah(),
                    hasilBiner.langkah()
                ));

                System.out.println();
            }
        }

        int totalLinear = 0;
        int totalBiner = 0;

        System.out.println("PERBANDINGAN HASIL (1 langkah = 1 NIM diperiksa)");
        System.out.printf(
            "%-14s | %-19s | %-17s | %-17s%n",
            "NIM", "Hasil", "Linear Search", "Binary Search"
        );
        System.out.println(
            "---------------+---------------------+-------------------+------------------"
        );

        for (Ringkasan hasil : ringkasan) {
            System.out.printf(
                "%-14s | %-19s | %-17d | %-17d%n",
                hasil.nim(),
                hasil.nama(),
                hasil.langkahLinear(),
                hasil.langkahBiner()
            );

            totalLinear += hasil.langkahLinear();
            totalBiner += hasil.langkahBiner();
        }

        System.out.printf(
            "TOTAL pemeriksaan: Linear = %d, Binary = %d.%n",
            totalLinear, totalBiner
        );

        System.out.printf(
            "RATA-RATA pemeriksaan: Linear = %.2f, Binary = %.2f per pencarian.%n",
            totalLinear / 3.0, totalBiner / 3.0
        );

        System.out.println(
            "Kompleksitas teoritis: Linear O(n); Binary O(log n), dengan syarat data sudah terurut."
        );
    }
}