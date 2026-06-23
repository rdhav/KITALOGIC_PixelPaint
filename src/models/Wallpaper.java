/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author user
 */

public class Wallpaper  {
    
    protected int id;
    protected String title;
    protected String category;
    protected String imagePath;
    protected String description;
    protected int userId;
    protected String timeAdded;

    public Wallpaper(){}

    public Wallpaper(int id, String title, String category, String imagePath, String description, int userId,String timeAdded) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.imagePath = imagePath;
        this.description = description;
        this.userId = userId;
        this.timeAdded = timeAdded;
    }

    // --- GETTER AND SETTER (Mengontrol akses variabel beraliran enkapsulasi) ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }
    
    public String getUsernameFromId() {
    String result = "Unknown";
    String sql = "SELECT username FROM users WHERE id = ?";

    try (java.sql.Connection con = database.DBConnection.getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, this.userId);
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
