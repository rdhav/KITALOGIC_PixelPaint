package dao;

import database.DBConnection;
import models.User;
import models.RegularUser;
import models.AdminUser;
import java.sql.*;

public class UserDAO {

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