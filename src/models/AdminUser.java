/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Merepresentasikan pengguna dengan hak akses administrator dalam aplikasi
 * PixelPaint. Subclass dari {@link User} ini memiliki kemampuan tambahan
 * untuk mengelola seluruh konten dan pengguna lain melalui halaman
 * {@code ManageFrame}.
 * Setiap {@code AdminUser} yang dibuat otomatis memiliki role tetap
 * {@code "admin"}, sehingga tidak perlu menentukan role secara manual saat
 * instansiasi.
 *
 *
 * @author Nice
 */
public class AdminUser extends User {

    /**
     * Membuat objek {@code AdminUser} baru dengan role yang otomatis
     * di-set menjadi {@code "admin"}.
     *
     * @param id       ID unik pengguna
     * @param username nama pengguna (username) untuk login
     * @param password kata sandi pengguna
     * @param bio      biodata singkat pengguna
     */
    public AdminUser(int id, String username, String password, String bio) {
        super(id, username, password, bio, "admin"); // super constructor
    }

    /**
     * Mengembalikan judul dashboard yang ditampilkan khusus untuk admin,
     * meng-override method {@link User#getDashboardTitle()} dari superclass.
     *
     * @return judul dashboard dalam format {@code "Dashboard - <username>"}
     */
    // Overriding
    @Override
    public String getDashboardTitle() {
        return "Dashboard - " + super.getUsername(); // super method
    }

    /**
     * Mengembalikan pesan sambutan khusus untuk admin yang baru login,
     * berisi nama pengguna dan instruksi singkat untuk mengelola konten.
     *
     * @return pesan sambutan dalam format
     *         {@code "Halo Admin <username>! Kelola konten PixelPaint di sini."}
     */
    public String getAdminWelcome() {
        return "Halo Admin " + getUsername() + "! Kelola konten PixelPaint di sini.";
    }
}