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
public interface GalleryProvider {
    //abstract method
    List<Wallpaper> getGalleryWallpaper(int userId);
    
    //default method
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
