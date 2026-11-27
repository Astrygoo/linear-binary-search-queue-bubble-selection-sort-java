import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class SimulasiAntrean {
    public static void main(String[] args) {
        Queue<String> antrean = new ArrayDeque<>();

        // Sepuluh pembeli awal sebagai data uji.
        String[] pembeliAwal = {
            "Andi", "Bella", "Caca", "Dedi", "Evi",
            "Fajar", "Gina", "Hendra", "Intan", "Joko"
        };

        for (String nama : pembeliAwal) {
            antrean.offer(nama);
        }

        Scanner input = new Scanner(System.in);

        System.out.println("SIMULASI ANTREAN PEMBELI KANTIN");
        tampilkanAntrean(antrean);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambahkan pembeli");
            System.out.println("2. Layani pembeli berikutnya");
            System.out.println("3. Tampilkan antrean");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");

            String pilihan = input.nextLine().trim();

            switch (pilihan) {
                case "1":
                    System.out.print("Masukkan nama pembeli: ");
                    String nama = input.nextLine().trim();

                    if (nama.isEmpty()) {
                        System.out.println("Nama tidak boleh kosong.");
                    } else {
                        antrean.offer(nama);
                        System.out.println(
                            nama + " berhasil ditambahkan ke belakang antrean."
                        );
                        System.out.println(
                            "Jumlah pembeli dalam antrean: " + antrean.size()
                        );
                    }
                    break;

                case "2":
                    String dilayani = antrean.poll();

                    if (dilayani == null) {
                        System.out.println("Antrean kosong. Tidak ada pembeli untuk dilayani.");
                    } else {
                        System.out.println("Pembeli yang dilayani: " + dilayani);
                        System.out.println(
                            "Sisa pembeli dalam antrean: " + antrean.size()
                        );
                    }
                    break;

                case "3":
                    tampilkanAntrean(antrean);
                    break;

                case "4":
                    System.out.println("Program antrean selesai.");
                    input.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid. Masukkan angka 1 sampai 4.");
            }
        }
    }

    private static void tampilkanAntrean(Queue<String> antrean) {
        System.out.println("\nIsi antrean (depan ke belakang):");

        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }

        int posisi = 1;
        for (String nama : antrean) {
            System.out.println(posisi + ". " + nama);
            posisi++;
        }
    }
}