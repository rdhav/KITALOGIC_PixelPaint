/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Nice
 */


/**
 * Kelas utilitas untuk mengelola koneksi ke database MySQL.
 * Menyediakan metode statis untuk mendapatkan objek {@link Connection}
 * yang terhubung ke database {@code pixelpaint_db} melalui JDBC.
 *
 * @author Nice
 */
public class DBConnection {
    /** URL koneksi JDBC ke database MySQL. */
    private static final String URL = "jdbc:mysql://localhost:3306/pixelpaint_db";
    /** Username untuk autentikasi database. */
    private static final String USER = "root";
     /** Password untuk autentikasi database. Sesuaikan dengan konfigurasi lokal. */
    private static final String PASSWORD = ""; // atur dengan password kalian

    /**
     * Membuat dan mengembalikan koneksi ke database.
     * Menggunakan {@link DriverManager} untuk membangun koneksi berdasarkan
     * URL, username, dan password yang telah dikonfigurasi. Pesan status
     * koneksi akan dicetak ke konsol.
     *
     * @return objek {@link Connection} jika koneksi berhasil, {@code null} jika gagal
     */
    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal!");
            e.printStackTrace();
        }

        return conn;
    }
}
