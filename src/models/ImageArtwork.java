/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Nice
 */
public class ImageArtwork extends Artwork {

    private String fileFormat; // jpg, png, dll

    public ImageArtwork(int id, String title, String description, String category, String imagePath, int userId, String fileFormat) {
        super(id, title, description, category, imagePath, userId); // super constructor
    }

    public String getFileFormat() { return fileFormat; }
    public void setFileFormat(String fileFormat) { this.fileFormat = fileFormat; }

    // Overriding
    @Override
    public String getArtworkType() {
        return "Image Artwork (." + fileFormat + ")";
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " | Format: " + fileFormat; // super method
    }
}