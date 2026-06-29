/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nice
 */
public class AdminUser extends User {

    public AdminUser(int id, String username, String password, String bio) {
        super(id, username, password, bio, "admin"); // super constructor
    }

    // Overriding
    @Override
    public String getDashboardTitle() {
        return "Dashboard - " + super.getUsername(); // super method
    }

    public String getAdminWelcome() {
        return "Halo Admin " + getUsername() + "! Kelola konten PixelPaint di sini.";
    }
}