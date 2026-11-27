# Algoritma dan Struktur Data dengan Java

**Implementasi Java untuk lima soal pencarian, antrean, perhitungan, dan pengurutan.**

Kumpulan program Java untuk mempelajari cara kerja algoritma pencarian dan pengurutan, struktur data antrean, serta pendekatan iteratif dan rekursif. Setiap program menampilkan data uji dan hasil pengukuran agar prosesnya bisa dipelajari, dijalankan ulang, dan dibandingkan.

## Materi yang dibahas

| Materi | Jenis | Kegunaan dalam proyek |
|---|---|---|
| Linear Search | Algoritma pencarian | Memeriksa NIM mahasiswa dari data pertama sampai target ditemukan atau data habis. |
| Binary Search | Algoritma pencarian | Memeriksa bagian tengah lalu mempersempit separuh rentang pencarian; data harus terurut. |
| Queue (FIFO) | Struktur data | Mengelola antrean pembeli; yang datang lebih dulu dilayani lebih dulu. |
| Iteratif | Pendekatan pemecahan masalah | Menjumlahkan transaksi menggunakan perulangan. |
| Rekursif | Pendekatan pemecahan masalah | Menjumlahkan transaksi melalui pemanggilan fungsi sampai kondisi berhenti tercapai. |
| Bubble Sort | Algoritma pengurutan | Mengurutkan nilai menurun dengan membandingkan pasangan bersebelahan. |
| Selection Sort | Algoritma pengurutan | Memilih nilai tertinggi dari bagian yang belum terurut untuk mengisi posisi berikutnya. |
| Bubble Sort dengan penghentian dini | Optimasi algoritma | Mengakhiri pengurutan lebih awal jika satu putaran tidak menukar data. |

Queue bukan algoritma pencarian atau pengurutan: Queue adalah struktur data yang menentukan urutan data masuk dan keluar. Iteratif dan rekursif juga merupakan dua cara menyusun penyelesaian masalah, bukan algoritma pengurutan.

## Ringkasan kompleksitas

`n` menyatakan jumlah data yang diproses. Kompleksitas menjelaskan bagaimana kebutuhan waktu atau ruang bertambah saat ukuran data meningkat.

| Materi / operasi | Kompleksitas waktu | Ruang tambahan | Syarat atau catatan |
|---|---:|---:|---|
| Linear Search | O(n) | O(1) | Tidak membutuhkan data terurut. |
| Binary Search | O(log n) | O(1) | Data harus diurutkan terlebih dahulu. |
| Queue: tambah (`offer`) | O(1) amortisasi | - | Implementasi menggunakan `ArrayDeque`. |
| Queue: layani (`poll`) | O(1) amortisasi | - | Mengambil pembeli paling depan. |
| Queue: tampilkan seluruh isi | O(n) | - | Setiap pembeli perlu dibaca dan dicetak. |
| Penjumlahan iteratif | O(n) | O(1) | Setiap transaksi diproses sekali. |
| Penjumlahan rekursif | O(n) | O(n) | Stack menyimpan pemanggilan fungsi. |
| Bubble Sort | O(n²) | O(1) | Perbandingan pasangan bersebelahan. |
| Selection Sort | O(n²) | O(1) | Memindai bagian yang belum terurut pada tiap putaran. |
| Bubble Sort dengan penghentian dini | Best case O(n), worst case O(n²) | O(1) | Putaran berhenti jika tidak ada pertukaran. |


## Struktur proyek

```text
AnalisisAlgoritmaJava/
├── Soal1Pencarian/
│   └── PencarianMahasiswa.java
├── Soal2Queue/
│   └── SimulasiAntrean.java
├── Soal3TotalBelanja/
│   └── TotalBelanja.java
├── Soal4Sorting/
│   └── PerbandinganSorting.java
├── Soal5Optimasi/
│   └── OptimasiBubbleSort.java
├── bukti/       # transkrip keluaran program
├── build/       # hasil kompilasi; dibuat saat menjalankan perintah
├── .gitignore
└── README.md
```

## Menjalankan program

### Persyaratan

- JDK 17 atau lebih baru.
- Visual Studio Code dengan **Extension Pack for Java** (opsional, tetapi memudahkan pengeditan dan menjalankan Java).
- Windows PowerShell untuk perintah contoh di bawah.

Buka folder repository di VS Code, lalu pilih **Terminal > New Terminal**. Pastikan terminal berada di folder utama repository, yaitu folder yang berisi `README.md` dan folder `Soal1Pencarian`, `Soal2Queue`, dan seterusnya.

Buat folder hasil kompilasi dan kompilasi semua source code sekali:

```powershell
New-Item -ItemType Directory -Force build
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d build $files
```

Jalankan satu program dengan nama kelasnya:

```powershell
java -cp build PencarianMahasiswa
java -cp build SimulasiAntrean
java -cp build TotalBelanja
java -cp build PerbandinganSorting
java -cp build OptimasiBubbleSort
```

Soal 1 meminta tiga NIM berbeda, dan Soal 2 menerima pilihan menu serta nama pembeli. Jalankan keduanya di terminal terintegrasi VS Code agar kamu dapat mengetik input. Soal 3 sampai 5 mencetak data dan hasil eksperimen secara otomatis.

Folder `build/` berisi file `.class` hasil kompilasi, Folder `bukti/` berisi transkrip keluaran program. Folder itu dapat dibuat ulang dari source code dan tidak dimasukkan ke repository, `.gitignore` mengecualikannya.

## Isi soal dan perintah menjalankan

### Soal 1 - Linear Search dan Binary Search

File: `Soal1Pencarian/PencarianMahasiswa.java`

Program menampilkan 10 data mahasiswa, lalu meminta tiga NIM berbeda. Untuk setiap NIM, program menunjukkan pemeriksaan Linear Search dan Binary Search, kemudian mencetak tabel jumlah langkah. Data diurutkan berdasarkan NIM agar Binary Search dapat digunakan.

```powershell
javac -encoding UTF-8 -d build .\Soal1Pencarian\PencarianMahasiswa.java
java -cp build PencarianMahasiswa
```

Masukkan tiga NIM yang berbeda saat diminta. Contoh NIM yang tersedia: `25552011009`, `25552011006`, dan `25552011003`. Program juga menerima NIM yang tidak ada di daftar dan melaporkan bahwa data tidak ditemukan.

### Soal 2 - Simulasi Queue pembeli kantin

File: `Soal2Queue/SimulasiAntrean.java`

Antrean dimulai dengan 10 pembeli. Menu program menyediakan operasi untuk:

1. Menambahkan pembeli ke belakang antrean.
2. Melayani pembeli paling depan.
3. Menampilkan antrean dari depan ke belakang.
4. Keluar dari program.

Antrean mengikuti prinsip FIFO (*First In, First Out*).

```powershell
javac -encoding UTF-8 -d build .\Soal2Queue\SimulasiAntrean.java
java -cp build SimulasiAntrean
```

Kompleksitas operasi pada `ArrayDeque`: tambah (`offer`) O(1) amortisasi, layani (`poll`) O(1) amortisasi, tampilkan semua pembeli O(n), dan ruang antrean O(n).

### Soal 3 - Total belanja iteratif dan rekursif

File: `Soal3TotalBelanja/TotalBelanja.java`

Program menjumlahkan 10 transaksi yang sama dengan perulangan dan rekursi. Keluaran memperlihatkan langkah iteratif, proses rekursif saat masuk dan kembali, serta perbandingan hasil dan hitungan operasi. Kedua pendekatan menghasilkan total **Rp185.000**.

```powershell
javac -encoding UTF-8 -d build .\Soal3TotalBelanja\TotalBelanja.java
java -cp build TotalBelanja
```

Kedua pendekatan memiliki waktu O(n). Pendekatan iteratif menggunakan ruang tambahan O(1), sedangkan rekursif menggunakan ruang stack O(n).

### Soal 4 - Bubble Sort dan Selection Sort

File: `Soal4Sorting/PerbandinganSorting.java`

Program mengurutkan nilai 10 mahasiswa dari tertinggi ke terendah. Program menampilkan data awal, jumlah perbandingan dan pertukaran per putaran, hasil kedua pengurutan, dan tabel perbandingan.

```powershell
javac -encoding UTF-8 -d build .\Soal4Sorting\PerbandinganSorting.java
java -cp build PerbandinganSorting
```

Pada data yang tersedia, kedua algoritma melakukan 45 perbandingan. Bubble Sort melakukan 24 pertukaran dan Selection Sort melakukan 6 pertukaran. Keduanya memiliki waktu O(n²) dan ruang tambahan O(1).

### Soal 5 - Optimasi Bubble Sort pada 10, 50, dan 100 data

File: `Soal5Optimasi/OptimasiBubbleSort.java`

Soal ini mengembangkan Bubble Sort dari soal 4. Versi biasa selalu menyelesaikan seluruh putaran; versi optimasi berhenti setelah satu putaran tidak menghasilkan pertukaran. Program membuat data uji secara otomatis untuk ukuran 10, 50, dan 100.

Data uji hampir terurut menurun dengan dua angka terakhir tertukar. Program membandingkan jumlah perbandingan, pertukaran, putaran, dan total langkah. **Total langkah didefinisikan di program sebagai perbandingan + pertukaran**; pemeriksaan kondisi perulangan tidak dihitung.

```powershell
javac -encoding UTF-8 -d build .\Soal5Optimasi\OptimasiBubbleSort.java
java -cp build OptimasiBubbleSort
```

Hasil yang diharapkan untuk pola data uji tersebut:

| Jumlah data | Perbandingan biasa | Perbandingan optimasi | Pertukaran masing-masing | Total langkah biasa | Total langkah optimasi |
|---:|---:|---:|---:|---:|---:|
| 10 | 45 | 17 | 1 | 46 | 18 |
| 50 | 1.225 | 97 | 1 | 1.226 | 98 |
| 100 | 4.950 | 197 | 1 | 4.951 | 198 |

Perbandingan di loop bersarang adalah bagian yang paling banyak bekerja. Optimasi dapat mengurangi pemeriksaan pada data yang hampir terurut seperti pengujian ini. Worst-case kedua versi tetap O(n²); versi optimasi dapat mencapai O(n) pada data yang sudah terurut. Ruang tambahan keduanya O(1).


