/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;
import java.util.*;
import models.Wallpaper;

/**
 *
 * @author user
 */

/**
 * Kontrak untuk kelas-kelas yang menyediakan daftar wallpaper bagi galeri,
 * baik galeri publik (semua wallpaper dari semua pengguna) maupun galeri
 * privat (wallpaper milik satu pengguna tertentu).
 * Interface ini diimplementasikan oleh {@code WallpaperPublic} dan
 * {@code WallpaperPrivate}, yang masing-masing menentukan logika query
 * berbeda untuk method {@link #getGalleryWallpaper(int)}.
 *
 * @author user
 */
public interface GalleryProvider {
    //abstract method
    /**
     * Mengambil daftar wallpaper berdasarkan ID pengguna.
     * Implementasi konkretnya dapat berbeda tergantung jenis galeri:
     * galeri publik biasanya mengabaikan {@code userId} dan mengambil semua
     * wallpaper, sedangkan galeri privat memfilter hanya wallpaper milik
     * pengguna tersebut.
     *
     * @param userId ID pengguna yang dijadikan acuan pengambilan data
     * @return daftar objek {@link Wallpaper} sesuai implementasi kelas yang
     *         menerapkan interface ini; tidak boleh {@code null}, gunakan
     *         list kosong jika tidak ada data
     */
    List<Wallpaper> getGalleryWallpaper(int userId);
    
    //default method
     /**
     * Menyaring daftar wallpaper berdasarkan nama kategori tertentu.
     * Perbandingan kategori dilakukan tanpa memperhatikan besar-kecil huruf
     * (case-insensitive). Method ini merupakan default method sehingga
     * semua kelas yang mengimplementasikan {@link GalleryProvider} otomatis
     * mendapatkan fungsi ini tanpa perlu menulis ulang logikanya.
     *
     * @param daftarWallpaper daftar wallpaper yang akan disaring; jika
     *                        {@code null}, akan mengembalikan list kosong
     * @param kategori        nama kategori yang dicari; jika {@code null}
     *                        atau kosong, akan mengembalikan list kosong
     * @return daftar wallpaper yang kategorinya cocok dengan parameter
     *         {@code kategori}; list kosong jika tidak ada yang cocok atau
     *         parameter input tidak valid
     */
    default List<Wallpaper> filterByCategory(List<Wallpaper> daftarWallpaper, String kategori) {
        List<Wallpaper> hasilFilter = new ArrayList<>();
        
        if (daftarWallpaper == null || kategori == null || kategori.isEmpty()) {
            return hasilFilter;
        }
        
        for (Wallpaper wp : daftarWallpaper) {
            if (wp.getCategory() != null && wp.getCategory().equalsIgnoreCase(kategori)) {
                hasilFilter.add(wp);
            }
        }
        return hasilFilter;
    }
    
    //static method
    /**
     * Mengambil username dari database berdasarkan ID pengguna.
     * Method ini bersifat static sehingga dapat dipanggil langsung lewat
     * nama interface (contoh: {@code GalleryProvider.getUsernameFromId(5)})
     * tanpa perlu membuat objek implementasi terlebih dahulu. Umumnya
     * digunakan untuk menampilkan nama pengunggah pada kartu wallpaper atau
     * halaman detail.
     *
     * @param userId ID pengguna yang ingin dicari username-nya
     * @return username pengguna jika ditemukan di database, atau
     *         {@code "Unknown"} jika ID tidak ditemukan atau terjadi
     *         kesalahan saat mengakses database
     */
    static public String getUsernameFromId(int userId) {
        String result = "Unknown";
        String sql = "SELECT username FROM users WHERE id = ?";

        try (java.sql.Connection con = database.DBConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            java.sql.ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString("username");
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        return result;
    }   
}
