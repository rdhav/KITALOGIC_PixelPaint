/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import interfaces.GalleryProvider;
import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Subclass dari {@link Wallpaper} yang menyediakan implementasi galeri
 * privat, yaitu daftar wallpaper yang hanya berisi karya milik satu
 * pengguna tertentu.
 * Kelas ini digunakan terutama di halaman {@code ProfileFrame} dan
 * {@code PublicProfileFrame} untuk menampilkan koleksi wallpaper yang
 * dimiliki oleh pengguna yang sedang dilihat profilnya, berbeda dengan
 * {@code WallpaperPublic} yang menampilkan wallpaper dari semua pengguna.
 *
 * @author user
 */
public class WallpaperPrivate extends Wallpaper implements GalleryProvider{
    
    /**
     * Membuat objek {@code WallpaperPrivate} kosong tanpa nilai awal.
     */
    public WallpaperPrivate(){super();}
    
    
    /**
     * Membuat objek {@code WallpaperPrivate} baru lengkap dengan seluruh
     * atributnya.
     *
     * @param id          ID unik wallpaper
     * @param title       judul wallpaper
     * @param category    kategori wallpaper
     * @param imagePath   nama berkas gambar wallpaper
     * @param description deskripsi singkat wallpaper
     * @param userId      ID pengguna pemilik wallpaper
     * @param timeAdded   waktu wallpaper pertama kali diunggah
     * @param timeUpdated waktu wallpaper terakhir diperbarui
     */
    public WallpaperPrivate(int id, String title, String category, String imagePath, String description, int userId,String timeAdded,String timeUpdated) {
        super(id, title, category, imagePath, description,userId,timeAdded,timeUpdated);
    }
    
    
    /**
     * Mengambil seluruh wallpaper yang dimiliki oleh pengguna dengan ID
     * tertentu, diurutkan dari yang paling baru diunggah (ID terbesar
     * ditampilkan lebih dulu).
     * <p>
     * Implementasi ini meng-query tabel {@code artworks} dengan filter
     * {@code user_id}, sehingga hanya wallpaper milik pengguna tersebut
     * yang dikembalikan.
     *
     * @param userId ID pengguna yang wallpaper-nya ingin diambil
     * @return daftar wallpaper milik pengguna tersebut; list kosong jika
     *         pengguna tidak memiliki wallpaper atau terjadi kesalahan
     *         saat mengakses database
     */
    @Override
    public List<Wallpaper> getGalleryWallpaper(int userId) {
        
        List<Wallpaper> list = new ArrayList<>();
        
        String query = "SELECT * FROM artworks WHERE user_id = ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId); 
            
            ResultSet rs = pstmt.executeQuery();
            
                while (rs.next()) {
                    Wallpaper wp = new WallpaperPrivate(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getString("image_path"),
                        rs.getString("description"),
                        rs.getInt("user_id"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                    );
                    list.add(wp);
                }    
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }  
}
