/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

/**
 *
 * @author Nice
 */
/**
 * Kontrak untuk kelas-kelas yang merepresentasikan konten yang dapat
 * dikelola (disimpan dan dihapus) dalam aplikasi PixelPaint, seperti
 * wallpaper atau karya visual lainnya.
 * Interface ini diimplementasikan oleh kelas {@code Artwork} (dan
 * turunannya) untuk memastikan setiap konten yang bisa dikelola memiliki
 * perilaku dasar berupa penyimpanan dan penghapusan data.
 *
 * @author Nice
 */
public interface Manageable {
    // Abstract methods
    /**
     * Menyimpan data konten ke dalam media penyimpanan (database atau
     * berkas). Implementasi konkret menentukan cara penyimpanan yang
     * sesuai dengan jenis konten yang dikelola.
     */
    void save();
    
    /**
     * Menghapus data konten dari media penyimpanan (database atau
     * berkas). Implementasi konkret menentukan cara penghapusan yang
     * sesuai dengan jenis konten yang dikelola.
     */
    void delete();

    // Static method
    /**
     * Mengembalikan nama aplikasi tempat interface ini digunakan.
     * <p>
     * Method ini bersifat static sehingga dapat dipanggil langsung lewat
     * nama interface (contoh: {@code Manageable.getAppName()}) tanpa perlu
     * membuat objek implementasi terlebih dahulu.
     *
     * @return nama aplikasi, yaitu {@code "PixelPaint"}
     */
    static String getAppName() {
        return "PixelPaint";
    }

    // Default method
    /**
     * Mengembalikan deskripsi singkat mengenai konten beserta nama aplikasi
     * asalnya, dengan memanfaatkan {@link #getAppName()}.
     * <p>
     * Method ini bersifat default sehingga semua kelas yang
     * mengimplementasikan {@link Manageable} otomatis mendapatkan fungsi
     * ini tanpa perlu menulis ulang logikanya, kecuali ingin meng-override
     * dengan deskripsi yang lebih spesifik.
     *
     * @return string deskripsi konten dalam format
     *         {@code "Ini adalah konten dari aplikasi <nama aplikasi>"}
     */
    default String getInfo() {
        return "Ini adalah konten dari aplikasi " + getAppName();
    }
}