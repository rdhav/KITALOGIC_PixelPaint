/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nice
 */
public class RegularUser extends User {

    public RegularUser(int id, String username, String password, String bio) {
        super(id, username, password, bio, "user"); // super constructor
    }

    // Overloading constructor
    public RegularUser(int id, String username, String password) {
        super(id, username, password, "user"); // super constructor overloading
    }

    // Overriding
    @Override
    public String getDashboardTitle() {
        return "Dashboard - " + super.getUsername(); // super method
    }

    public String getWelcomeMessage() {
        return "Selamat datang, " + getUsername() + "! Yuk upload karya terbaikmu.";
    }
}