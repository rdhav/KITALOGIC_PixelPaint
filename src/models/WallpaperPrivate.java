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
 *
 * @author user
 */
public class WallpaperPrivate extends Wallpaper implements GalleryProvider{
    
    public WallpaperPrivate(){super();}
    
    public WallpaperPrivate(int id, String title, String category, String imagePath, String description, int userId,String timeAdded,String timeUpdated) {
        super(id, title, category, imagePath, description,userId,timeAdded,timeUpdated);
    }
    
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
