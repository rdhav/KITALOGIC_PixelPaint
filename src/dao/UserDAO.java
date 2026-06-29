package dao;

import database.DBConnection;
import models.User;
import models.RegularUser;
import models.AdminUser;
import java.sql.*;


/**
 * Data Access Object (DAO) untuk entitas {@link User}.
 * Menyediakan operasi database terkait pengguna, termasuk autentikasi,
 * registrasi, pembaruan profil, dan pencarian berdasarkan ID.
 */

public class UserDAO {
    
       /**
     * Melakukan autentikasi pengguna berdasarkan username dan password.
     * Metode ini mencari pengguna di database yang cocok dengan kombinasi
     * username dan password yang diberikan. Jika ditemukan, akan mengembalikan
     * instance {@link AdminUser} atau {@link RegularUser} sesuai dengan role-nya.
     *
     * @param username nama pengguna yang akan diautentikasi
     * @param password kata sandi pengguna
     * @return objek {@link User} jika autentikasi berhasil, {@code null} jika gagal
     */
    
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String pass = rs.getString("password");
                String bio = rs.getString("bio");
                String role = rs.getString("role");

                if (role.equals("admin")) {
                    user = new AdminUser(id, uname, pass, bio);
                } else {
                    user = new RegularUser(id, uname, pass, bio);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    
     /**
     * Mendaftarkan pengguna baru ke dalam sistem.
     * <p>
     * Metode ini terlebih dahulu memeriksa apakah username sudah digunakan.
     * Jika belum, data pengguna baru akan disimpan ke database dengan role
     * default {@code "user"} dan bio kosong.
     * </p>
     *
     * @param username nama pengguna yang ingin didaftarkan
     * @param password kata sandi pengguna baru
     * @return {@code true} jika registrasi berhasil, {@code false} jika username sudah terdaftar
     *         atau terjadi kesalahan database
     */
    public boolean register(String username, String password) {
        String checkSql = "SELECT id FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users (username, password, bio, role) VALUES (?, ?, '', 'user')";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, username);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                return false;
            }

            PreparedStatement insertPs = conn.prepareStatement(insertSql);
            insertPs.setString(1, username);
            insertPs.setString(2, password);
            insertPs.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    /**
     * Memperbarui data profil pengguna berdasarkan ID.
     * Sebelum melakukan pembaruan, metode ini memeriksa apakah username baru
     * sudah digunakan oleh pengguna lain. Jika {@code newPassword} kosong,
     * hanya username dan bio yang akan diperbarui tanpa mengubah password.
     *
     * @param userId      ID unik pengguna yang profilnya akan diperbarui
     * @param newUsername username baru yang diinginkan
     * @param newPassword password baru; jika kosong ({@code ""}), password tidak akan diubah
     * @param newBio      bio baru pengguna
     * @return {@code true} jika pembaruan berhasil, {@code false} jika username sudah dipakai
     *         oleh pengguna lain atau terjadi kesalahan database
     */
    public boolean updateProfile(int userId, String newUsername, String newPassword, String newBio) {
        String checkSql = "SELECT id FROM users WHERE username = ? AND id != ?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setString(1, newUsername);
            checkPs.setInt(2, userId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                return false; 
            }

            String updateSql;
            PreparedStatement updatePs;

            if (newPassword.isEmpty()) {
                updateSql = "UPDATE users SET username = ?, bio = ? WHERE id = ?";
                updatePs = conn.prepareStatement(updateSql);
                updatePs.setString(1, newUsername);
                updatePs.setString(2, newBio);
                updatePs.setInt(3, userId);
            } else {
                updateSql = "UPDATE users SET username = ?, password = ?, bio = ? WHERE id = ?";
                updatePs = conn.prepareStatement(updateSql);
                updatePs.setString(1, newUsername);
                updatePs.setString(2, newPassword);
                updatePs.setString(3, newBio);
                updatePs.setInt(4, userId);
            }

            updatePs.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    /**
     * Mengambil data pengguna berdasarkan ID.
     * Metode ini melakukan query ke database untuk mencari pengguna dengan ID
     * yang diberikan. Hasilnya berupa instance {@link AdminUser} atau
     * {@link RegularUser} tergantung pada role yang tersimpan di database.
     *
     * @param userId ID unik pengguna yang ingin dicari
     * @return objek {@link User} jika ditemukan, {@code null} jika tidak ada pengguna
     *         dengan ID tersebut atau terjadi kesalahan database
     */
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                if (role.equals("admin")) {
                    return new models.AdminUser(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("bio")
                    );
                } else {
                    return new models.RegularUser(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("bio")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}