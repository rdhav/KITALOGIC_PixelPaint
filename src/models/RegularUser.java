/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Merepresentasikan pengguna biasa (non-admin) dalam aplikasi PixelPaint.
 * Subclass dari {@link User} ini memiliki hak akses standar seperti
 * mengunggah, melihat, dan mengelola wallpaper miliknya sendiri melalui
 * halaman {@code HomeFrame} dan {@code ProfileFrame}.
 * Setiap {@code RegularUser} yang dibuat otomatis memiliki role tetap
 * {@code "user"}, sehingga tidak perlu menentukan role secara manual saat
 * instansiasi.
 *
 * @author Nice
 */
public class RegularUser extends User {

    /**
     * Membuat objek {@code RegularUser} baru lengkap dengan biodata,
     * dengan role yang otomatis di-set menjadi {@code "user"}.
     *
     * @param id       ID unik pengguna
     * @param username nama pengguna (username) untuk login
     * @param password kata sandi pengguna
     * @param bio      biodata singkat pengguna
     */
    public RegularUser(int id, String username, String password, String bio) {
        super(id, username, password, bio, "user"); // super constructor
    }

    // Overloading constructor
    /**
     * Membuat objek {@code RegularUser} baru tanpa biodata (overload dari
     * constructor utama), biasanya digunakan saat pengguna baru pertama
     * kali mendaftar dan belum mengisi bio.
     *
     * @param id       ID unik pengguna
     * @param username nama pengguna (username) untuk login
     * @param password kata sandi pengguna
     */
    public RegularUser(int id, String username, String password) {
        super(id, username, password, "user"); // super constructor overloading
    }

    // Overriding
    /**
     * Mengembalikan judul dashboard yang ditampilkan untuk pengguna biasa,
     * meng-override method {@link User#getDashboardTitle()} dari superclass.
     *
     * @return judul dashboard dalam format {@code "Dashboard - <username>"}
     */
    @Override
    public String getDashboardTitle() {
        return "Dashboard - " + super.getUsername(); // super method
    }

    /**
     * Mengembalikan pesan sambutan untuk pengguna biasa yang baru login,
     * berisi ajakan untuk mengunggah karya.
     *
     * @return pesan sambutan berisi nama pengguna dan ajakan mengunggah karya
     */
    public String getWelcomeMessage() {
        return "Selamat datang, " + getUsername() + "! Yuk upload karya terbaikmu.";
    }
}