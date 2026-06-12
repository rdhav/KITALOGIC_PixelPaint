/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import interfaces.Manageable;

/**
 *
 * @author Nice
 */
public class Artwork implements Manageable {
    private int id;
    private String title;
    private String description;
    private String category;
    private String imagePath;
    private int userId;

    // Constructor
    public Artwork(int id, String title, String description, String category, String imagePath, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.imagePath = imagePath;
        this.userId = userId;
    }

    // Overloading constructor (tanpa description)
    public Artwork(int id, String title, String category, String imagePath, int userId) {
        this.id = id;
        this.title = title;
        this.description = "";
        this.category = category;
        this.imagePath = imagePath;
        this.userId = userId;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    // Implementasi interface Manageable
    @Override
    public void save() {
        System.out.println("Artwork '" + title + "' disimpan.");
    }

    @Override
    public void delete() {
        System.out.println("Artwork '" + title + "' dihapus.");
    }

    // Override default method dari interface
    @Override
    public String getInfo() {
        return "Artwork: " + title + " | Kategori: " + category;
    }

    public String getArtworkType() {
        return "Artwork Umum";
    }
}