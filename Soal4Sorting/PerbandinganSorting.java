public class PerbandinganSorting {
    record Mahasiswa(String nama, int nilai) {}

    record HasilUrut(
        Mahasiswa[] data,
        int perbandingan,
        int pertukaran
    ) {}

    private static Mahasiswa[] dataAwal() {
        return new Mahasiswa[] {
            new Mahasiswa("Alya Putri", 78),
            new Mahasiswa("Bima Pratama", 92),
            new Mahasiswa("Citra Lestari", 65),
            new Mahasiswa("Danu Saputra", 88),
            new Mahasiswa("Eka Maharani", 74),
            new Mahasiswa("Farhan Akbar", 95),
            new Mahasiswa("Gita Permata", 81),
            new Mahasiswa("Hadi Wijaya", 69),
            new Mahasiswa("Intan Sari", 90),
            new Mahasiswa("Joko Santoso", 84)
        };
    }

    private static HasilUrut bubbleSort(Mahasiswa[] dataAsli) {
        Mahasiswa[] data = dataAsli.clone();
        int totalPerbandingan = 0;
        int totalPertukaran = 0;

        System.out.println("\nPROSES BUBBLE SORT");

        for (int i = 0; i < data.length - 1; i++) {
            int perbandinganPutaran = 0;
            int pertukaranPutaran = 0;

            for (int j = 0; j < data.length - 1 - i; j++) {
                // Membandingkan nilai dua mahasiswa yang bersebelahan.
                totalPerbandingan++;
                perbandinganPutaran++;

                // Tukar jika nilai kiri lebih kecil agar urut menurun.
                if (data[j].nilai() < data[j + 1].nilai()) {
                    Mahasiswa sementara = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = sementara;

                    totalPertukaran++;
                    pertukaranPutaran++;
                }
            }

            System.out.printf(
                "Putaran %d: %d perbandingan, %d pertukaran.%n",
                i + 1, perbandinganPutaran, pertukaranPutaran
            );
        }

        return new HasilUrut(data, totalPerbandingan, totalPertukaran);
    }

    private static HasilUrut selectionSort(Mahasiswa[] dataAsli) {
        Mahasiswa[] data = dataAsli.clone();
        int totalPerbandingan = 0;
        int totalPertukaran = 0;

        System.out.println("\nPROSES SELECTION SORT");

        for (int i = 0; i < data.length - 1; i++) {
            int indeksNilaiTertinggi = i;
            int perbandinganPutaran = 0;

            // Cari nilai tertinggi pada bagian yang belum terurut.
            for (int j = i + 1; j < data.length; j++) {
                totalPerbandingan++;
                perbandinganPutaran++;

                if (data[j].nilai() > data[indeksNilaiTertinggi].nilai()) {
                    indeksNilaiTertinggi = j;
                }
            }

            int pertukaranPutaran = 0;

            // Letakkan nilai tertinggi di posisi berikutnya.
            if (indeksNilaiTertinggi != i) {
                Mahasiswa sementara = data[i];
                data[i] = data[indeksNilaiTertinggi];
                data[indeksNilaiTertinggi] = sementara;

                totalPertukaran++;
                pertukaranPutaran = 1;
            }

            System.out.printf(
                "Putaran %d: %d perbandingan, %d pertukaran.%n",
                i + 1, perbandinganPutaran, pertukaranPutaran
            );
        }

        return new HasilUrut(data, totalPerbandingan, totalPertukaran);
    }

    private static void tampilkanData(String judul, Mahasiswa[] data) {
        System.out.println("\n" + judul);
        for (Mahasiswa mahasiswa : data) {
            System.out.printf(
                "%-16s nilai %d%n",
                mahasiswa.nama(), mahasiswa.nilai()
            );
        }
    }

    public static void main(String[] args) {
        Mahasiswa[] data = dataAwal();

        tampilkanData("NILAI AWAL 10 MAHASISWA", data);

        HasilUrut hasilBubble = bubbleSort(data);
        HasilUrut hasilSelection = selectionSort(data);

        tampilkanData("HASIL BUBBLE SORT (TERTINGGI KE TERENDAH)", hasilBubble.data());
        tampilkanData("HASIL SELECTION SORT (TERTINGGI KE TERENDAH)", hasilSelection.data());

        System.out.println("\nPERBANDINGAN HASIL");
        System.out.printf(
            "%-18s | %-15s | %-12s | %-18s%n",
            "Algoritma", "Perbandingan", "Pertukaran", "Kompleksitas waktu"
        );
        System.out.println(
            "-------------------+-----------------+--------------+-------------------"
        );
        System.out.printf(
            "%-18s | %-15d | %-12d | %-18s%n",
            "Bubble Sort",
            hasilBubble.perbandingan(),
            hasilBubble.pertukaran(),
            "O(n^2)"
        );
        System.out.printf(
            "%-18s | %-15d | %-12d | %-18s%n",
            "Selection Sort",
            hasilSelection.perbandingan(),
            hasilSelection.pertukaran(),
            "O(n^2)"
        );

        System.out.println(
            "\nSatu perbandingan dihitung setiap kali dua nilai mahasiswa dibandingkan."
        );
        System.out.println(
            "Satu pertukaran dihitung setiap kali posisi dua mahasiswa ditukar."
        );
    }
}