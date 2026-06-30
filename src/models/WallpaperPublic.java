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
 * publik, yaitu daftar seluruh wallpaper dari semua pengguna tanpa
 * memandang siapa pengunggahnya.
 * Kelas ini digunakan terutama di halaman {@code HomeFrame} untuk
 * menampilkan galeri eksplorasi umum, berbeda dengan
 * {@link WallpaperPrivate} yang hanya menampilkan wallpaper milik satu
 * pengguna tertentu.
 *
 * @author user
 */
public class WallpaperPublic extends Wallpaper implements GalleryProvider{
    
    
    /**
     * Membuat objek {@code WallpaperPublic} kosong tanpa nilai awal.
     */
    public WallpaperPublic() {super();}
    
    
    /**
     * Membuat objek {@code WallpaperPublic} baru lengkap dengan seluruh
     * atributnya.
     *
     * @param id          ID unik wallpaper
     * @param title       judul wallpaper
     * @param category    kategori wallpaper
     * @param imagePath   nama berkas gambar wallpaper
     * @param description deskripsi singkat wallpaper
     * @param userId      ID pengguna yang mengunggah wallpaper
     * @param timeAdded   waktu wallpaper pertama kali diunggah
     * @param timeUpdated waktu wallpaper terakhir diperbarui
     */
    public WallpaperPublic(int id, String title, String category, String imagePath, String description, int userId,String timeAdded,String timeUpdated) {
     super(id, title, category, imagePath, description,userId,timeAdded,timeUpdated);
    }
    
    
    /**
     * Mengambil seluruh wallpaper dari semua pengguna, diurutkan dari yang
     * paling baru diunggah (ID terbesar ditampilkan lebih dulu).
     * Implementasi ini meng-query seluruh isi tabel {@code artworks} tanpa
     * filter, sehingga parameter {@code userId} tidak digunakan dalam
     * query namun tetap dipertahankan agar sesuai dengan kontrak interface
     * {@link GalleryProvider#getGalleryWallpaper(int)}.
     *
     * @param userId parameter ini tidak memengaruhi hasil query, hanya
     *               diperlukan untuk memenuhi kontrak interface
     * @return daftar seluruh wallpaper yang ada di database; list kosong
     *         jika tidak ada data atau terjadi kesalahan saat mengakses
     *         database
     */
    @Override
    public List<Wallpaper> getGalleryWallpaper(int userId) {
        List<Wallpaper> list = new ArrayList<>();
        
        String query = "SELECT * FROM artworks ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
           ResultSet rs = pstmt.executeQuery();
          
                while (rs.next()) {
                    Wallpaper wp = new WallpaperPublic(
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
    
    
    /**
     * Mengambil username pengguna berdasarkan ID dari database.
     * Method ini bersifat static sehingga dapat dipanggil langsung lewat
     * nama kelas (contoh: {@code WallpaperPublic.getUsernameById(5)})
     * tanpa perlu membuat objek terlebih dahulu.
     *
     * @param userId ID pengguna yang ingin dicari username-nya
     * @return username pengguna jika ditemukan, atau {@code "Unknown"}
     *         jika ID tidak ditemukan atau terjadi kesalahan saat
     *         mengakses database
     */
    public static String getUsernameById(int userId) {
        String username = "Unknown";
        String query = "SELECT username FROM users WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
}
