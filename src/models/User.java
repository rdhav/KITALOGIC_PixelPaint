/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Merepresentasikan data dasar seorang pengguna dalam aplikasi PixelPaint,
 * mencakup informasi akun seperti ID, username, password, biodata, dan role.
 * Kelas ini berfungsi sebagai superclass dari {@link RegularUser} dan
 * {@link AdminUser}, yang masing-masing menambahkan perilaku khusus sesuai
 * peran penggunanya. Seluruh field dienkapsulasi sebagai {@code private}
 * dan hanya dapat diakses melalui getter/setter yang disediakan.
 *
 * @author Nice
 */
public class User {
    // Enkapsulasi: semua field private
    /** ID unik pengguna. */
    private int id;
 
    /** Nama pengguna (username) untuk login. */
    private String username;
 
    /** Kata sandi pengguna. */
    private String password;
 
    /** Biodata singkat pengguna. */
    private String bio;
 
    /** Peran pengguna, contohnya {@code "user"} atau {@code "admin"}. */
    private String role;

    // Constructor
    /**
     * Membuat objek {@code User} baru lengkap dengan biodata.
     *
     * @param id       ID unik pengguna
     * @param username nama pengguna (username) untuk login
     * @param password kata sandi pengguna
     * @param bio      biodata singkat pengguna
     * @param role     peran pengguna (contoh: {@code "user"}, {@code "admin"})
     */
    public User(int id, String username, String password, String bio, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.role = role;
    }

    // Overloading constructor (tanpa bio)
    /**
     * Membuat objek {@code User} baru tanpa biodata (overload dari
     * constructor utama). Bio akan otomatis diisi string kosong.
     *
     * @param id       ID unik pengguna
     * @param username nama pengguna (username) untuk login
     * @param password kata sandi pengguna
     * @param role     peran pengguna (contoh: {@code "user"}, {@code "admin"})
     */
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.bio = "";
        this.role = role;
    }

    // Getter & Setter
    /**
     * Mengembalikan ID unik pengguna.
     *
     * @return ID pengguna
     */
    public int getId() { return id; }
    /**
     * Mengubah ID pengguna.
     *
     * @param id ID baru yang akan di-set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Mengembalikan username pengguna.
     *
     * @return username pengguna
     */
    public String getUsername() { return username; }
    /**
     * Mengubah username pengguna.
     *
     * @param username username baru yang akan di-set
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Mengembalikan password pengguna.
     *
     * @return password pengguna
     */
    public String getPassword() { return password; }
    /**
     * Mengubah password pengguna.
     *
     * @param password password baru yang akan di-set
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Mengembalikan biodata singkat pengguna.
     *
     * @return bio pengguna
     */
    public String getBio() { return bio; }
    /**
     * Mengubah biodata singkat pengguna.
     *
     * @param bio bio baru yang akan di-set
     */
    public void setBio(String bio) { this.bio = bio; }

    /**
     * Mengembalikan peran (role) pengguna.
     *
     * @return role pengguna (contoh: {@code "user"}, {@code "admin"})
     */
    public String getRole() { return role; }
    /**
     * Mengubah peran (role) pengguna.
     *
     * @param role role baru yang akan di-set
     */
    public void setRole(String role) { this.role = role; }

    // Method yang akan di-override
    /**
     * Mengembalikan judul dashboard default untuk pengguna. Method ini
     * dirancang untuk di-override oleh subclass ({@link RegularUser} dan
     * {@link AdminUser}) agar menampilkan judul yang lebih spesifik sesuai
     * perannya.
     *
     * @return judul dashboard default, yaitu {@code "Dashboard User"}
     */
    public String getDashboardTitle() {
        return "Dashboard User";
    }

    @Override
    /**
     * Mengembalikan representasi string dari objek {@code User}, berisi
     * ID, username, dan role. Berguna untuk keperluan debugging atau
     * logging.
     *
     * @return string dalam format
     *         {@code "User[id=<id>, username=<username>, role=<role>]"}
     */
    public String toString() {
        return "User[id=" + id + ", username=" + username + ", role=" + role + "]";
    }
}