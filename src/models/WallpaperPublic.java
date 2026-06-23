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
public class WallpaperPublic extends Wallpaper implements GalleryProvider{
    
    public WallpaperPublic() {super();}
    
    public WallpaperPublic(int id, String title, String category, String imagePath, String description, int userId,String timeAdded) {
     super(id, title, category, imagePath, description,userId,timeAdded);
    }
    
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
                        rs.getString("created_at")
                    );
                    list.add(wp);
                }
                
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return list;       
    } 
      
}
