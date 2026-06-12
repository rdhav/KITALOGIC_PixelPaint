/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nice
 */
public class DigitalPainting extends Artwork {

    private String softwareUsed; // Photoshop, Procreate, dll

    public DigitalPainting(int id, String title, String description, String category, String imagePath, int userId, String softwareUsed) {
        super(id, title, description, category, imagePath, userId); // super constructor
        this.softwareUsed = softwareUsed;
    }

    public String getSoftwareUsed() { return softwareUsed; }
    public void setSoftwareUsed(String softwareUsed) { this.softwareUsed = softwareUsed; }

    // Overriding
    @Override
    public String getArtworkType() {
        return "Digital Painting";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Software: " + softwareUsed; // super method
    }
}