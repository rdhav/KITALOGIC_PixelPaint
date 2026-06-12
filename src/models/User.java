/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nice
 */
public class User {
    // Enkapsulasi: semua field private
    private int id;
    private String username;
    private String password;
    private String bio;
    private String role;

    // Constructor
    public User(int id, String username, String password, String bio, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.role = role;
    }

    // Overloading constructor (tanpa bio)
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.bio = "";
        this.role = role;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Method yang akan di-override
    public String getDashboardTitle() {
        return "Dashboard User";
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", username=" + username + ", role=" + role + "]";
    }
}